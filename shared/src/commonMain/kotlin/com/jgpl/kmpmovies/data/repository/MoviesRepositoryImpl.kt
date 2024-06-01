package com.jgpl.kmpmovies.data.repository

import com.jgpl.kmpmovies.data.source.remote.MoviesRemoteSource
import com.jgpl.kmpmovies.domain.model.Movie
import com.jgpl.kmpmovies.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val remoteSource: MoviesRemoteSource
) : MoviesRepository {
    override suspend fun getMoviesPopular(): Result<List<Movie>> {
        return remoteSource.getMoviesPopular()
    }
}