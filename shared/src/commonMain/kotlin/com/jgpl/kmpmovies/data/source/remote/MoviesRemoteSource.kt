package com.jgpl.kmpmovies.data.source.remote

import com.jgpl.kmpmovies.data.source.remote.dto.PopularMoviesResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

interface MoviesRemoteSource {
    suspend fun getMoviesPopular(): Result<PopularMoviesResponseDTO>
}

class MoviesRemoteSourceImpl(
    private val httpClient: HttpClient
) : MoviesRemoteSource {

    override suspend fun getMoviesPopular(): Result<PopularMoviesResponseDTO> = runCatching {
        val response: HttpResponse = httpClient.get(MoviesUrls.MOVIES_POPULAR)
        return if (response.status == HttpStatusCode.OK) {
            val movies = Json.decodeFromString<PopularMoviesResponseDTO>(response.body())
            Result.success(movies)
        } else {
            Result.failure(Exception("Error fetching popular movies"))
        }
    }
}