package com.example.demospring.controllers

import com.example.demospring.dtos.AccountDto
import com.example.demospring.dtos.SignInRequestDto
import com.example.demospring.dtos.SignInResponseDto
import com.example.demospring.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AccountController {

    final AccountService accountService;

    @Autowired
    AccountController(AccountService accountService) {
        this.accountService = accountService
    }

    @PostMapping("/sign-in")
    SignInResponseDto signIn(@RequestBody SignInRequestDto requestDto) {
        return accountService.SignIn(requestDto)
    }
}
