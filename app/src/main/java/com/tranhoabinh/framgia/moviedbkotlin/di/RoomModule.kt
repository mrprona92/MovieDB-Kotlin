package com.tranhoabinh.framgia.moviedbkotlin.di

import android.content.Context
import androidx.room.Room
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomContract
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomMovieDataSource
import org.koin.dsl.module.module

val roomModule = module(override = true) {
    single { createDatabaseName() }
    single { createAppDatabase(get(), get()) }
    single { createMovieDao(get()) }
}

fun createDatabaseName() = RoomContract.DATABASE_MOVIE

fun createAppDatabase(dbName: String, context: Context) = Room.databaseBuilder(context, RoomMovieDataSource::class.java, dbName).fallbackToDestructiveMigration().build()

fun createMovieDao(appDatabase: RoomMovieDataSource) = appDatabase.movieDao()
