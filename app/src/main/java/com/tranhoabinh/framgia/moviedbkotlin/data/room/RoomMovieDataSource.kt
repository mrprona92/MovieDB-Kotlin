package com.tranhoabinh.framgia.moviedbkotlin.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

@Database(
        entities = arrayOf(Movie::class),
        version = 1)
abstract class RoomMovieDataSource : RoomDatabase() {

    abstract fun movieDao(): RoomMovieDao

    companion object {
        fun buildPersistentMovie(context: Context): RoomMovieDataSource = Room.databaseBuilder(
                context.applicationContext,
                RoomMovieDataSource::class.java,
                RoomContract.DATABASE_MOVIE
        ).build()

    }
}

