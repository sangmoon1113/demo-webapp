package com.example.demospring.controllers

import groovy.util.logging.Slf4j
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.util.logging.Logger

@Slf4j
@Tag(name = "예제", description = "예제")
@RestController
@RequestMapping("/api")
class SampleController {

    private final Environment environment

    SampleController(@Autowired Environment  environment) {
        this.environment = environment;
    }

    @Operation(summary = "Hello")
    @GetMapping(value = "/hello", produces = "text/plain")
    String getHello() {
        def car = environment.getProperty("appconfig.sample.car");
        def fruits = environment.getProperty("appconfig.sample.fruits", String[].class);

        log.info(String.format("car=%s", car));
        log.info(String.format("fruits=%s", String.join(",", fruits)));
        log.debug("aaa");
        log.error("aaa");
        return "Hello World";
    }
}
