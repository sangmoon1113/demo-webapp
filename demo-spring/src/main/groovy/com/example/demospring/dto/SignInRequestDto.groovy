package com.example.demospring.dto

import io.swagger.v3.oas.annotations.media.Schema

class SignInRequestDto {
    @Schema(example = "username")
    String username;

    @Schema(example = "password")
    String password;
}
