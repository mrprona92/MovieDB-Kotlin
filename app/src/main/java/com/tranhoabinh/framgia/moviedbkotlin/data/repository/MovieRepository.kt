package com.tranhoabinh.framgia.moviedbkotlin.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomMovieDataSource
import erikjhordanrey.android_kotlin_devises.data.repository.Repository
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
        private val roomMovieDataSource: RoomMovieDataSource) : Repository {

    override fun getMovieList(): LiveData<List<Movie>> {

        val mutableLiveData = MutableLiveData<List<Movie>>()
        //TODO Binh.TH 24/09 get data from DAO
        return mutableLiveData
    }

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()
}
