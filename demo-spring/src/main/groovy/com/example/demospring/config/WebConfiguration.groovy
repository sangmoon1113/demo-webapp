package com.example.demospring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.firewall.StrictHttpFirewall
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration implements WebMvcConfigurer {
    @Override
    void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseTrailingSlashMatch(true);
    }


    /*
        https://github.com/spring-projects/spring-framework/issues/31366
        https://docs.spring.io/spring-security/site/docs/4.2.x/reference/html/security-filter-chain.html#request-matching
        https://github.com/spring-projects/spring-security/issues/5343#issuecomment-389022251

        StrictHttpFirewall .setAllowUrlEncodedDoubleSlash(true)
        URL 로 인코딩된 "%2F%2F"인 이중 슬래시 "//"를 경로에 허용할지 여부를 결정합니다.

        "https://velog.io/@this-is-spear/PathPatternParser-%EB%8F%99%EC%9E%91-%EC%9D%B4%ED%95%B4#antpathmatcher%EC%97%90%EC%84%9C-pathpatternparser%EB%A1%9C-%EB%B3%80%EA%B2%BD%EB%90%9C-%EC%9D%B4%EC%9C%A0"
     */
    @Bean
    StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true);
        firewall.setAllowBackSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }
}
