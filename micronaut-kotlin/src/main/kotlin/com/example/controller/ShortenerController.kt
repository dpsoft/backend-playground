package com.example.controller

import arrow.core.Either
import com.example.service.ShortenerService
import com.example.util.LogSupport
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import java.net.URI
import javax.inject.Inject

@Introspected
data class LongURL(@JsonProperty("long-url") val value: String)

@Controller("/api")
class ShortenerController(@Inject val service: ShortenerService): LogSupport {

    @Post("/shorten")
    fun post(@Body longURL:LongURL): String {
        log.info("Incoming longURL ${longURL.value}")
        return when(val createShortURL = service.createShortURL(longURL.value)) {
            is Either.Right -> createShortURL.b.shortURL
            is Either.Left -> {
                val errorMessage = "Error: ${createShortURL.a.cause}"
                log.error(errorMessage, createShortURL.a)
                errorMessage
            }
       }
    }

    @Get("/go")
    fun go(shortURL: String): HttpResponse<String> {
        log.info("Incoming shortURL $shortURL")
        return when (val url = service.get(shortURL)) {
            is Either.Right -> {
                 if (url.b == null) HttpResponse.notFound(shortURL)
                 else HttpResponse.redirect(URI.create(url.b!!.longURL))
            }
            is Either.Left -> {
                val errorMessage = "Error: ${url.a.cause}"
                log.error(errorMessage, url.a)
                HttpResponse.serverError(errorMessage)
            }
        }
    }
}