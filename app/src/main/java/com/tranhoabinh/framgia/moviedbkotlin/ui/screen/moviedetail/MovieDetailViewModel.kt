package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import androidx.lifecycle.MutableLiveData
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieDetailViewModel constructor(
        private val repository: Repository
) : BaseViewModel() {
    val movie = MutableLiveData<Movie>().apply { value = null }
    val title = MutableLiveData<String>().apply { value = "" }
    val releaseDate = MutableLiveData<String>().apply { value = "" }
    val posterPath = MutableLiveData<String>().apply { value = "" }
    val fullPath = MutableLiveData<String>().apply { value = "" }
    val overview = MutableLiveData<String>().apply { value = "" }

    fun requestMovieDetail(movieId: String) {
        compositeDisposable.add(
                repository.getMovieDetail(movieId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { isLoading.value = true }
                        .doFinally {
                            isLoading.value = false
                        }
                        .subscribe({
                            title.value = it.title
                            releaseDate.value = it.release_date
                            posterPath.value = it.poster_path
                            fullPath.value = it.backdrop_path
                            overview.value = it.overview
                            movie.value = it
                        }, { e -> onLoadFail(e) }))
    }

    fun requestCheckFavorite(movieId: String) {
        compositeDisposable.add(
                repository.findMovieById(movieId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { isLoading.value = true }
                        .doFinally {
                            isLoading.value = false
                        }
                        .subscribe({
                            movie.value?.isFavorite = it.isFavorite
                        }, { e -> onLoadFail(e) }))
    }

    fun handleFavoriteClick(movieId: String) {
        compositeDisposable.add(
                repository.findMovieById(movieId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { isLoading.value = true }
                        .doFinally {
                            isLoading.value = false
                        }
                        .subscribe({
                            if (it == null) {
                                movie.value?.isFavorite = true
                                repository.addFavoriteMovie(movie.value)
                            } else {
                                movie.value?.apply { isFavorite = !isFavorite }
                                repository.updateFavoriteMovie(movie.value)
                            }
                        }, { e -> onLoadFail(e) })
        )
    }

    interface OnItemClick {
        fun onFavoriteClick()
    }
}
