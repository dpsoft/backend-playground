package repository

import arrow.core.Either
import com.example.model.URL
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton

interface URLRepository {
    fun save(id: String, data: URL): Either<Throwable, URL>
    fun get(id: String): Either<Throwable, URL?>
}

@Singleton
class CacheRepository: URLRepository {
    private val cache:MutableMap<String, URL> = ConcurrentHashMap()

    override fun save(id: String, data: URL): Either<Throwable, URL> {
        cache[id] = data
        return Either.right(data)
    }

    override fun get(id: String): Either<Throwable, URL?> {
        return Either.right(cache[id])
    }
}