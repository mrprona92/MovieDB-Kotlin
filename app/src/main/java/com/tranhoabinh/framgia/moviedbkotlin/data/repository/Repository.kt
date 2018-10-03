package com.tranhoabinh.framgia.moviedbkotlin.data.repository

import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import io.reactivex.Maybe
import io.reactivex.Single

interface Repository {
    fun getMovieList(page: Int): Single<GetMoviesResponse>

    fun getMovieDetail(movieId: String): Single<Movie>

    fun addFavoriteMovie(movie: Movie?)

    fun updateFavoriteMovie(movie: Movie?)

    fun findMovieById(movieId: String): List<Movie>

    fun getMovieFavorites(limit:Int, offset:Int): Maybe<List<Movie>>
}
