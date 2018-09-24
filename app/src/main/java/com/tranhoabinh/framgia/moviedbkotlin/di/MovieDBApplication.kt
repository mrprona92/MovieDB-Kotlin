package com.tranhoabinh.framgia.moviedbkotlin.di

import android.app.Application

class MovieDBApplication : Application() {

  companion object {
    lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    initializeDagger()
  }

  private fun initializeDagger() {
    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .roomModule(RoomModule())
        .remoteModule(RemoteModule()).build()
  }
}

