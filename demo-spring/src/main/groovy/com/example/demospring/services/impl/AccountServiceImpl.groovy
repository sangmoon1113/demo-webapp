package com.example.demospring.services.impl

import com.example.demospring.config.security.JwtTokenProvider
import com.example.demospring.dao.AccountDAO
import com.example.demospring.dto.SignInRequestDto
import com.example.demospring.dto.SignInResponseDto
import com.example.demospring.services.AccountService
import com.warrenstrange.googleauth.GoogleAuthenticator
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class AccountServiceImpl implements AccountService {

    final JwtTokenProvider jwtTokenProvider;
    final AccountDAO dao;

    AccountServiceImpl(JwtTokenProvider jwtTokenProvider, @Autowired AccountDAO dao) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.dao = dao;
    }

    @Override
    SignInResponseDto SignIn(SignInRequestDto requestDto) {
        def responseDto = new SignInResponseDto(username: requestDto.username);
        def entity = dao.findByUsername(requestDto.username);
        if(entity != null) {
            if(entity.password == requestDto.password) {
                responseDto.success = true;
                def roles = new ArrayList<String>();
                responseDto.token = jwtTokenProvider.createToken("test", roles);
            }
        }

        return responseDto;
    }

    void resetOtpKey(String username) {
        def entity = dao.findByUsername(username);
        if (entity != null) {
            def googleAuthenticator = new GoogleAuthenticator();
            def googleAuthenticatorKey = googleAuthenticator.createCredentials();
            entity.optKey = googleAuthenticatorKey.getKey();
            dao.save(entity);
        }
    }

    String getOtpKey(String username) {
        def entity = dao.findByUsername(username);
        if (entity != null) {
            return entity.optKey;
        } else {
            return "";
        }
    }

    Boolean authorizeOtpValue(String username, int value) {
        def entity = dao.findByUsername(username);
        if (entity != null) {
            def key = entity.optKey;
            def googleAuthenticator = new GoogleAuthenticator();
            def verify = googleAuthenticator.authorize(key, value);
            log.info("Verify : {}", verify);

            return verify;
        } else {
            return false;
        }
    }
}
