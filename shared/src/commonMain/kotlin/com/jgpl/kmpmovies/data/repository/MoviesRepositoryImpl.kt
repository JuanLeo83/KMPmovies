package com.jgpl.kmpmovies.data.repository

import com.jgpl.kmpmovies.domain.model.Movie
import com.jgpl.kmpmovies.domain.repository.MoviesRepository

class MoviesRepositoryImpl : MoviesRepository {
    override suspend fun getMoviesList(): Result<List<Movie>> {
        TODO("Not yet implemented")
    }
}