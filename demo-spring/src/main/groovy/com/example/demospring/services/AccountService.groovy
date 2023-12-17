package com.example.demospring.services

import com.example.demospring.dtos.AccountDto
import com.example.demospring.dtos.SignInRequestDto
import com.example.demospring.dtos.SignInResponseDto
import org.springframework.stereotype.Service

interface AccountService {
    SignInResponseDto SignIn(SignInRequestDto requestDto);
    void resetOtpKey(String username);
    String getOtpKey(String username);
    Boolean authorizeOtpValue(String username, int value);
}