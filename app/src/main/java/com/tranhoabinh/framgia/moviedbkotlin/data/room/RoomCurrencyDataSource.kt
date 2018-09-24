package com.tranhoabinh.framgia.moviedbkotlin.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

@Database(
        entities = arrayOf(Movie::class),
        version = 1)
abstract class RoomCurrencyDataSource : RoomDatabase() {

    abstract fun movieDao(): RoomMovieDao

    companion object {
        fun buildPersistentCurrency(context: Context): RoomCurrencyDataSource = Room.databaseBuilder(
                context.applicationContext,
                RoomCurrencyDataSource::class.java,
                RoomContract.DATABASE_CURRENCY
        ).build()

    }
}

