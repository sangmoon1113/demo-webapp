package com.example.demospring.services


import com.example.demospring.dto.SignInRequestDto
import com.example.demospring.dto.SignInResponseDto

interface AccountService {
    SignInResponseDto SignIn(SignInRequestDto requestDto);
    void resetOtpKey(String username);
    String getOtpKey(String username);
    Boolean authorizeOtpValue(String username, int value);
}