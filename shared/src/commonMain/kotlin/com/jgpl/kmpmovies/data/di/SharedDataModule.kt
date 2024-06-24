package com.jgpl.kmpmovies.data.di

import com.jgpl.kmpmovies.data.repository.MoviesRepositoryImpl
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSource
import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSourceImpl
import com.jgpl.kmpmovies.data.source.remote.MoviesUrls
import com.jgpl.kmpmovies.data.source.remote.mapper.MovieRemoteMapper
import com.jgpl.kmpmovies.domain.repository.MoviesRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import org.koin.dsl.module

val sharedDataModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(remoteSource = get()) }

    single<HttpClient> {
        getHttpClient()
    }

    single { MovieRemoteMapper() }

    single<MoviesRemoteSource> {
        MoviesRemoteSourceImpl(
            httpClient = get(),
            mapper = get()
        )
    }
}

expect fun getHttpClient(
    logger: Logger = Logger.SIMPLE,
    level: LogLevel = LogLevel.ALL,
    requestTimeoutMillis: Long = 30_000,
    connectTimeoutMillis: Long = 10_000,
    socketTimeoutMillis: Long = 30_000,
    host: String = MoviesUrls.HOST
): HttpClient