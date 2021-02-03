package com.example.service

import arrow.core.Either
import com.example.model.URL
import me.nimavat.shortid.ShortId
import repository.URLRepository
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

interface ShortenerService {
    fun createShortURL(longURL: String): Either<Throwable, URL>
    fun get(shortURL: String): Either<Throwable, URL?>
}

@Singleton
class Shortener (@Inject val repository: URLRepository): ShortenerService {
    override fun createShortURL(longURL: String): Either<Throwable, URL> {
        val urlCode = ShortId.generate()
        val shortURL = "shortener/$urlCode"
        val url = URL(longURL, shortURL, Instant.now().toString())

        return repository.save(shortURL, url)
    }

    override fun get(shortURL: String): Either<Throwable, URL?> {
        return repository.get(shortURL)
    }
}