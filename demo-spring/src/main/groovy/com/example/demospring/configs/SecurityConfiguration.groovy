package com.example.demospring.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .formLogin()
                .loginPage("/admin/login")
                //.successHandler(new LoginAuthHandler())

                .and()
                .rememberMe()
                .key("key")
                .tokenValiditySeconds(86400 * 30) // 1달
                .rememberMeParameter("remember-me")

                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                //.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                .logoutSuccessUrl("/admin/login")
                .invalidateHttpSession(true);

        return httpSecurity.build();
    }

//    @Override
//    void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/api/sign-in").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/product/**").permitAll()
//                .antMatchers("**exception**").permitAll()
//                .anyRequest().hasRole("ADMIN")
//                .and()
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
//                .and()
//                .exceptionHandling().authenticationENtryPoint(new CustomAuthenticationEntiryPoint())
//                .and()
//                .addFilterBefor(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//    }
}
