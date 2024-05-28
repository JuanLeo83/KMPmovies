package com.jgpl.kmpmovies.domain.usecase

import com.jgpl.kmpmovies.domain.repository.MoviesRepository

class GetMoviesListUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() = repository.getMoviesList()
}