package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import com.tranhoabinh.framgia.moviedbkotlin.core.BaseViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieDetailViewModel constructor(
        private val repository: Repository
) : BaseViewModel() {
    fun requestMovieDetail(movieId: String) {
        compositeDisposable.add(
                repository.getMovieDetail(movieId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { isLoading.value = true }
                        .doFinally {
                            isLoading.value = false
                        }
                        .subscribe({
                            //TODO Update data to view
                        }, { e -> onLoadFail(e) }))
    }
}
