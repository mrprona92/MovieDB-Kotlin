package com.tranhoabinh.framgia.moviedbkotlin.data.repository

import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import io.reactivex.Single

interface Repository {
    fun getMovieList(page: Int): Single<GetMoviesResponse>
}
