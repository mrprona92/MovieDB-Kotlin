package com.tranhoabinh.framgia.moviedbkotlin.data.repository

import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import io.reactivex.Single

interface Repository {
    fun getMovieList(page: Int): Single<GetMoviesResponse>

    fun getMovieDetail(movieId: String): Single<Movie>

    fun addFavoriteMovie(movie: Movie?): Long

    fun updateFavoriteMovie(movie: Movie?): Int

    fun findMovieById(movieId: String): Single<Movie>

    fun getMovieFavorites(limit:Int, offset:Int): Single<List<Movie>>
}
