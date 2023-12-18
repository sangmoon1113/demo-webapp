package com.example.demospring.config.security


import groovy.util.logging.Slf4j
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

@Slf4j
class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        def token = jwtTokenProvider.resolveToken(servletRequest);
        log.info("[doFilterInternal] token 값 추출 완료. token : {}", token);

        log.info("[doFilterInternal] token 값 유효성 체크 시작");
        if(token != null && jwtTokenProvider.validateToken(token)) {
            def authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("[doFilterInternal] token 값 유효성 체크 완료");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
