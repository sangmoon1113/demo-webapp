package com.example.demospring.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SampleController {
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }
}
