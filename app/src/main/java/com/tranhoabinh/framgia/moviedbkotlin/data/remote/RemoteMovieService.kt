package com.tranhoabinh.framgia.moviedbkotlin.data.remote

import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteMovieService {

    @GET(RemoteContract.MOVIELIST)
    fun requestMovies(
            @Query(RemoteContract.PAGE) page: Int
    ): Single<GetMoviesResponse>
}


