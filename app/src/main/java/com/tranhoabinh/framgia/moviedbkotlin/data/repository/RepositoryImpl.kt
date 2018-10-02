package com.tranhoabinh.framgia.moviedbkotlin.data.repository

import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.RemoteMovieService
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.response.GetMoviesResponse
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomMovieDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl constructor(
        private val remoteMovieService: RemoteMovieService,
        private val roomMovieDao: RoomMovieDao
) : Repository {
    override fun getMovieDetail(movieId: String): Single<Movie> {
        return remoteMovieService.requestMovieDetail(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getMovieList(page: Int): Single<GetMoviesResponse> {
        return remoteMovieService.requestMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    override fun addFavoriteMovie(movie: Movie?): Long {
        return roomMovieDao.insertMovie(movie)
    }

    override fun updateFavoriteMovie(movie: Movie?): Int {
        return roomMovieDao.updateFavoriteMovie(movie)
    }

    override fun getMovieFavorites(limit: Int, offset: Int): Single<List<Movie>> {
        return roomMovieDao.getFavoriteMovies(limit, offset)
    }

    override fun findMovieById(movieId: String): Single<Movie> {
        return roomMovieDao.findById(movieId)
    }
}
