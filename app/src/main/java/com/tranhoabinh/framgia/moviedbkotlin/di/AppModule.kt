package com.tranhoabinh.framgia.moviedbkotlin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val movieDBApplication: MovieDBApplication) {

  @Provides @Singleton fun provideContext(): Context = movieDBApplication

}
