package com.example.demospring


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class DemoSpringApplication {
    static void main(String[] args) {
        SpringApplication.run(DemoSpringApplication, args)
    }
}
