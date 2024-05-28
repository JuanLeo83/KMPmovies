package com.jgpl.kmpmovies.di

import com.jgpl.kmpmovies.data.di.sharedDataModule
import com.jgpl.kmpmovies.domain.di.sharedDomainModule

fun sharedModule() = listOf(
    sharedDataModule,
    sharedDomainModule
)