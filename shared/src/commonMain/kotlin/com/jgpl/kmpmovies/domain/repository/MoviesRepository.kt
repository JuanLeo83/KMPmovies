package com.jgpl.kmpmovies.domain.repository

import com.jgpl.kmpmovies.domain.model.Movie

interface MoviesRepository {
    suspend fun getMoviesList(): Result<List<Movie>>
}