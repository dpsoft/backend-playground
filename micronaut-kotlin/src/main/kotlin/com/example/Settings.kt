package com.example

import com.typesafe.config.Config
import io.github.config4k.*
import io.micronaut.core.annotation.Introspected

@Introspected
data class Settings(val server: Server, val shortener: Shortener) {
    companion object {
        data class Server(val port: Int)
        data class Shortener(val shortUrlDomain: String)

        @JvmStatic
        fun from(config: Config): Settings {
            val server = config.extract<Server>("url-shortener.server")
            val shortener = config.extract<Shortener>("url-shortener.shortener")
            return Settings(
                server = server,
                shortener = shortener
            )
        }
    }
}

