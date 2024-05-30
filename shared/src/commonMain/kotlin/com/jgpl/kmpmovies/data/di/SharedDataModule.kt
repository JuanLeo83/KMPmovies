package com.jgpl.kmpmovies.data.di

import com.jgpl.kmpmovies.data.repository.MoviesRepositoryImpl
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteConfig
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSource
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSourceImpl
import com.jgpl.kmpmovies.data.source.remote.MoviesUrls
import com.jgpl.kmpmovies.domain.repository.MoviesRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val sharedDataModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl() }

    single<HttpClient> {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
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
    }

    single<MoviesRemoteSource> { MoviesRemoteSourceImpl(httpClient = get()) }
}