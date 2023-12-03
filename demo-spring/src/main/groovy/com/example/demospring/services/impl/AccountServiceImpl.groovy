package com.example.demospring.services.impl


import com.example.demospring.dtos.SignInRequestDto
import com.example.demospring.dtos.SignInResponseDto
import com.example.demospring.services.AccountService
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl implements AccountService {

    @Override
    SignInResponseDto SignIn(SignInRequestDto requestDto) {
        def responseDto = new SignInResponseDto();
        if(requestDto.username == "test")
        {
            responseDto.success = true;
            responseDto.username = "test";
        }

        return responseDto;
    }
}
