package com.jgpl.kmpmovies.domain.di

import com.jgpl.kmpmovies.domain.usecase.GetMoviesListUseCase
import org.koin.dsl.module

val sharedDomainModule = module {
    single { GetMoviesListUseCase(get()) }
}