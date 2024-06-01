package com.jgpl.kmpmovies

import android.app.Application
import com.jgpl.kmpmovies.di.androidModule
import com.jgpl.kmpmovies.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KMPMoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KMPMoviesApplication)
            androidLogger()
            modules(sharedModule() + androidModule)
        }
    }
}