package com.example.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/shortener")
class PingController {

    @Get("/ping")
    fun ping(): String {
        return "pong"
    }
}