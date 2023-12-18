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

    static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication, args)
    }
}
