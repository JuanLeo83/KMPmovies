package com.jgpl.kmpmovies.di

import com.jgpl.kmpmovies.screen.home.HomeMapper
import com.jgpl.kmpmovies.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { HomeMapper() }
}