package com.example.demospring.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@OpenAPIDefinition(
    info = @Info(
            title = "OpenAPI definition",
            description = "API Specification<br/>...<br/>...",
            version = "v1"
    )
)
@Configuration
class OpenApiConfig {
}