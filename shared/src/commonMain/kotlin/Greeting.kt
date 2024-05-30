import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteConfig
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSourceImpl
import com.jgpl.kmpmovies.data.source.remote.MoviesUrls
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 30000
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = MoviesUrls.HOST
                parameters.append("api_key", MoviesRemoteConfig.API_KEY)
            }
        }
    }

    private val source = MoviesRemoteSourceImpl(httpClient = httpClient)

    suspend fun doAction(): String {
        val result = source.getMoviesPopular().getOrNull()
        if (result != null) {
            println(result.toString())
        }

        return ""
    }
}