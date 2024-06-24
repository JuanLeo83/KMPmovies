package com.jgpl.kmpmovies.data.di

import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun getHttpClient(
    logger: Logger,
    level: LogLevel,
    requestTimeoutMillis: Long,
    connectTimeoutMillis: Long,
    socketTimeoutMillis: Long,
    host: String
) = HttpClient(OkHttp) {
    install(Logging) {
        this.logger = logger
        this.level = level
    }

    install(HttpTimeout) {
        this.requestTimeoutMillis = requestTimeoutMillis
        this.connectTimeoutMillis = connectTimeoutMillis
        this.socketTimeoutMillis = socketTimeoutMillis
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
            this.host = host
            parameters.append("api_key", MoviesRemoteConfig.API_KEY)
        }
        headers {
            append("Content-Type", "application/json")
            append("Accept", "application/json")
        }
    }
}