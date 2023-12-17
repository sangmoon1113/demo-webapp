package com.example.demospring

import com.warrenstrange.googleauth.GoogleAuthenticator
import com.warrenstrange.googleauth.GoogleAuthenticatorKey
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
class DemoSpringApplication {

    static  String tempKey;
    static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication, args)

//        GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
//        GoogleAuthenticatorKey googleAuthenticatorKey = googleAuthenticator.createCredentials();
//
//        // 실제론 생성한 key를 DB에 저장해놔야 나중에 OTP를 검증할 수 있음
//        String key = googleAuthenticatorKey.getKey();
//        tempKey = key;
//        log.info("key : " + key);
//
//        String QRUrl = GoogleAuthenticatorQRGenerator.getOtpAuthURL("myDemoSpring", "myUsername", googleAuthenticatorKey);
//        log.info("QR URL : " + QRUrl);
    }
}
