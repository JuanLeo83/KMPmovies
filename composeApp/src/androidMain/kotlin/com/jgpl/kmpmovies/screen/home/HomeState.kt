package com.jgpl.kmpmovies.screen.home

import com.jgpl.kmpmovies.screen.home.vo.MovieItemVo

class HomeState(
    val isLoading: Boolean = false,
    val movies: List<MovieItemVo> = emptyList()
)