package com.tranhoabinh.framgia.moviedbkotlin.data.remote

import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(private val remoteMovieService: RemoteMovieService) {

    fun requestMovies(page: String) =
            remoteMovieService.requestMovies(page)
}

