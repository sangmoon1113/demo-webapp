package com.example.demospring.controllers

import com.example.demospring.DemoSpringApplication
import com.example.demospring.configs.JwtTokenProvider
import com.example.demospring.dtos.AccountDto
import com.example.demospring.dtos.SignInRequestDto
import com.example.demospring.dtos.SignInResponseDto
import com.example.demospring.services.AccountService
import com.warrenstrange.googleauth.GoogleAuthenticator
import com.warrenstrange.googleauth.GoogleAuthenticatorKey
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator
import groovy.util.logging.Slf4j
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.server.RequestPath
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Slf4j
@Tag(name = "계정", description = "계정")
@RestController
@RequestMapping("/api")
class AccountController {

    final AccountService accountService;
    final JwtTokenProvider jwtTokenProvider;
    @Autowired
    AccountController(AccountService accountService, JwtTokenProvider jwtTokenProvider) {
        this.accountService = accountService
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/sign-in")
    SignInResponseDto signIn(@RequestBody SignInRequestDto requestDto) {
        return accountService.SignIn(requestDto)
    }

    @Parameters([
        @Parameter(name = "X-AUTH-TOKEN", required = true, in = ParameterIn.HEADER, schema = @Schema(implementation = String.class))
    ])
    @PostMapping("/test")
    void test(@RequestHeader("X-AUTH-TOKEN") String header) {
        def result = jwtTokenProvider.validateToken(header)
        System.out.println(result);
        def a = "";
        def b = "";
    }

    // 인증 필요
    @Operation(summary = "OTP Key 갱신")
    @PutMapping("/otp/{username}")
    void RefreshAccountOptKey(@PathVariable("username") String username) {
        accountService.resetOtpKey(username);
    }

    // 인증 필요
    @Operation(summary = "OTP Key 조회")
    @GetMapping("/otp/{username}")
    Map<String, Object> GetAccountOptKey(@PathVariable("username") String username) {
        def key= accountService.getOtpKey(username);
        def url = GoogleAuthenticatorQRGenerator.getOtpAuthURL("myDemoSpring", username, new GoogleAuthenticatorKey.Builder(key).build());
        return Map.of("username", username, "key", key, "url", url);
    }

    // 인증 불필요
    @Operation(summary = "OTP Code 검증")
    @PostMapping("/otp/{username}/auth")
    Boolean AuthAccountOpt(@PathVariable("username") String username, @RequestParam("value") int value) {
        return accountService.authorizeOtpValue(username, value);
    }
}
