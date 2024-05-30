package com.jgpl.kmpmovies.domain.repository

import com.jgpl.kmpmovies.domain.model.Movie

interface MoviesRepository {
    suspend fun getMoviesPopular(): Result<List<Movie>>
}