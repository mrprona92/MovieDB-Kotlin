package com.tranhoabinh.framgia.moviedbkotlin.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class MovieDBApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
                appModule,
                remoteModule,
                roomModule,
                viewModelModule
        ))
    }
}



