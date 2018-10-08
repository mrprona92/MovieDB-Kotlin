package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import androidx.lifecycle.MutableLiveData
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MovieDetailViewModel constructor(
        private val repository: Repository
) : BaseViewModel() {
    var movie = MutableLiveData<Movie>()
    val isFavorited = MutableLiveData<Boolean>()
    val isFavoriteChanged = MutableLiveData<Boolean>()
    val isRefresh = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>().apply { value = "" }
    val releaseDate = MutableLiveData<String>().apply { value = "" }
    val posterPath = MutableLiveData<String>().apply { value = "" }
    val fullPath = MutableLiveData<String>().apply { value = "" }
    val overview = MutableLiveData<String>().apply { value = "" }

    fun requestMovieDetail(movieId: String) {
        compositeDisposable.add(
                repository.getMovieDetail(movieId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe {
                            isLoading.value = true
                        }
                        .doFinally {
                            isLoading.value = false
                            isRefresh.value = false
                            requestCheckFavorite(movieId)
                        }
                        .subscribe({
                            title.value = it.title
                            releaseDate.value = it.release_date
                            posterPath.value = it.poster_path
                            fullPath.value = it.backdrop_path
                            overview.value = it.overview
                            movie.value = it
                            onLoadSuccess(0)
                        }, { e ->
                            onLoadFail(e)
                        }))
    }

    private fun requestCheckFavorite(movieId: String) {
        doAsync {
            val movieList = repository.findMovieById(movieId)
            uiThread {
                if (!movieList.isEmpty()) {
                    val movieNew = movieList[0]
                    movie.value = movieNew
                    isFavorited.value = movieNew.isFavorite
                }
            }
        }
    }

    fun handleFavoriteClick(movieId: String) {
        doAsync {
            var movieList = repository.findMovieById(movieId)
            if (movieList.isEmpty()) {
                movie.value?.isFavorite = true
                repository.addFavoriteMovie(movie.value)
            } else {
                movie.value?.apply {
                    isFavorite = !isFavorite!!
                    repository.updateFavoriteMovie(movie.value)
                }
            }
            uiThread {
                isFavorited.value = movie.value?.isFavorite
                isFavoriteChanged.value = isFavorited.value
            }
        }
    }

    interface OnItemClick {
        fun onFavoriteClick()
    }
}
