package com.jgpl.kmpmovies.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jgpl.kmpmovies.domain.model.Movie
import com.jgpl.kmpmovies.domain.usecase.GetMoviesPopularUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesPopularUseCase: GetMoviesPopularUseCase,
    private val mapper: HomeMapper
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            _state.value = HomeState(isLoading = true)
            getMoviesPopularUseCase().fold(
                onSuccess = { movies -> handleGetPopularMoviesSuccess(movies) },
                onFailure = { handlePopularMoviesFailure() }
            )
        }
    }

    private fun handleGetPopularMoviesSuccess(movies: List<Movie>) {
        _state.value = HomeState(movies = mapper.mapToMovieItemVoList(movies))
    }

    private fun handlePopularMoviesFailure() {
        _state.value = HomeState()
    }
}

