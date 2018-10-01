package com.tranhoabinh.framgia.moviedbkotlin.data.remote

import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteMovieService {

    @GET(RemoteContract.MOVIE_LIST)
    fun requestMovies(
            @Query(RemoteContract.PAGE) page: Int
    ): Single<GetMoviesResponse>

    @GET(RemoteContract.MOVIE_DETAILS)
    fun requestMovieDetail(
            @Path("movie_id") movieId: String
    ): Single<Movie>
}


