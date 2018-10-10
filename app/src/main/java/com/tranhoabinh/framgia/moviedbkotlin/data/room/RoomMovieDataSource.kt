package com.tranhoabinh.framgia.moviedbkotlin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

@Database(
        entities = arrayOf(Movie::class),
        version = 1)
abstract class RoomMovieDataSource : RoomDatabase() {

    abstract fun movieDao(): RoomMovieDao

}

