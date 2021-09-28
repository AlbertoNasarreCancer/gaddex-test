package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/hello")
class Controller {

    @GetMapping("springboot")
    fun hello(): String {
        return "Hello, this is a REST endpoint"
    }
}