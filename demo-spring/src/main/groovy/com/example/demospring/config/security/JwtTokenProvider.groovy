package com.example.demospring.config.security

import com.example.demospring.dao.AccountDAO
import groovy.util.logging.Slf4j
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

import java.nio.charset.StandardCharsets

@Slf4j
@Component
class JwtTokenProvider {
    //private final UserDetailsService userDetailsService;
    private final AccountDAO dao;

    //@Value("my_secret_ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    String secretKey = "my_secret_ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final long tokenValidMillisecond = 1000L * 60 * 60;

    @Autowired
    JwtTokenProvider(AccountDAO dao) {
        this.dao = dao;
    }

    @PostConstruct
    protected void init() {
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    String createToken(String username, List<String> roles) {
        log.info("[createToken] 토큰 생성 시작");

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        Date now = new Date();

        String token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

        log.info("[createToken] 토큰 생성 완료");
        return token;
    }

    Authentication getAuthentication(String token) {
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        def userDetails = dao.findByUsername(this.getUsername(token));
        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    String getUsername(String token) {
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출");
        def info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);
        return  info;
    }

    String resolveToken(HttpServletRequest request) {
        log.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        return request.getHeader("X-AUTH-TOKEN");
    }

    boolean validateToken(String token) {
        log.info("[validateToken] 토큰 유효 체크 시작");
        try {
            def claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception ex) {
            log.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }
}
