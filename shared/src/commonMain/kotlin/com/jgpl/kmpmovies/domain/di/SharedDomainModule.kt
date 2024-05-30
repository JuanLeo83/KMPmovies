package com.jgpl.kmpmovies.domain.di

import com.jgpl.kmpmovies.domain.usecase.GetMoviesPopularUseCase
import org.koin.dsl.module

val sharedDomainModule = module {
    single { GetMoviesPopularUseCase(get()) }
}