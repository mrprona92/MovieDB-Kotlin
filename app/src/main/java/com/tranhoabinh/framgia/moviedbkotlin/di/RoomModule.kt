package com.tranhoabinh.framgia.moviedbkotlin.di

import android.content.Context
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomCurrencyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

  @Provides @Singleton fun provideRoomCurrencyDataSource(context: Context) =
      RoomCurrencyDataSource.buildPersistentCurrency(context)
}
