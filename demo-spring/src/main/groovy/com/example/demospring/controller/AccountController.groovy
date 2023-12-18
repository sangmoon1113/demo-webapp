package com.example.demospring.controller


import com.example.demospring.config.security.JwtTokenProvider
import com.example.demospring.dto.SignInRequestDto
import com.example.demospring.dto.SignInResponseDto
import com.example.demospring.services.AccountService
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
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
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

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    SignInResponseDto signIn(@RequestBody SignInRequestDto requestDto) {
        return accountService.SignIn(requestDto)
    }

    @Operation(summary = "Token 검증")
    @Parameters([
        @Parameter(name = "X-AUTH-TOKEN", required = true, in = ParameterIn.HEADER, schema = @Schema(implementation = String.class))
    ])
    @PostMapping("/validate-token")
    Boolean validateToken(@RequestHeader("X-AUTH-TOKEN") String header) {
        def result = jwtTokenProvider.validateToken(header)
        System.out.println(result);
        return result;
    }

//    @PostAuthorize("username == test")
    @PostMapping("/test1")
    void test1(@RequestHeader("X-AUTH-TOKEN") String header) {
        def a = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        def b = "";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/test2")
    void test2(@RequestHeader("X-AUTH-TOKEN") String header) {
        def a = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
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
