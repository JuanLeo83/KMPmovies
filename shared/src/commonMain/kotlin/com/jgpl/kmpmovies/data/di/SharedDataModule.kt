package com.jgpl.kmpmovies.data.di

import com.jgpl.kmpmovies.data.repository.MoviesRepositoryImpl
import com.jgpl.kmpmovies.domain.repository.MoviesRepository
import org.koin.dsl.module

val sharedDataModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl() }
}