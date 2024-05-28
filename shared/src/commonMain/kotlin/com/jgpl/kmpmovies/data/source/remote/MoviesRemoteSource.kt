package com.jgpl.kmpmovies.data.source.remote

import com.jgpl.kmpmovies.data.source.remote.dto.MovieDTO

interface MoviesRemoteSource {
    suspend fun getMoviesList(): Result<List<MovieDTO>>
}

class MoviesRemoteSourceImpl : MoviesRemoteSource {
    override suspend fun getMoviesList(): Result<List<MovieDTO>> {
        return Result.success(listOf(MovieDTO("Movie 1"), MovieDTO("Movie 2")))
    }
}