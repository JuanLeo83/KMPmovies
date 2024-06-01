package com.jgpl.kmpmovies.data.source.remote

import com.jgpl.kmpmovies.data.source.remote.dto.PopularMoviesResponseDTO
import com.jgpl.kmpmovies.data.source.remote.error.ApiRequestException
import com.jgpl.kmpmovies.data.source.remote.mapper.MovieRemoteMapper
import com.jgpl.kmpmovies.domain.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

interface MoviesRemoteSource {
    suspend fun getMoviesPopular(): Result<List<Movie>>
}

class MoviesRemoteSourceImpl(
    private val httpClient: HttpClient,
    private val mapper: MovieRemoteMapper
) : MoviesRemoteSource {

    override suspend fun getMoviesPopular(): Result<List<Movie>> = runCatching {
        val response: HttpResponse = httpClient.get(MoviesUrls.MOVIES_POPULAR)
        return if (response.status == HttpStatusCode.OK) {
            val moviesDto = Json.decodeFromString<PopularMoviesResponseDTO>(response.body())
            Result.success(mapper.toMovieModelList(moviesDto))
        } else {
            Result.failure(ApiRequestException(response.status.value, response.status.description))
        }
    }
}