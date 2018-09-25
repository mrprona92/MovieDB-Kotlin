package com.tranhoabinh.framgia.moviedbkotlin.di

import android.content.Context
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomMovieDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

  @Provides @Singleton fun provideRoomMovieDataSource(context: Context) =
      RoomMovieDataSource.buildPersistentMovie(context)
}
