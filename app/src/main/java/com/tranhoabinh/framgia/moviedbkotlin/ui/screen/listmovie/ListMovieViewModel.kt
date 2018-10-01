package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import com.tranhoabinh.framgia.moviedbkotlin.core.BaseListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MovieListViewModel constructor(
        private val repository: Repository
) : BaseListViewModel<Movie>() {

    override fun requestData(page: Int) {
        compositeDisposable.add(
                repository.getMovieList(page).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally {
                            isLoading.value = false
                            isRefresh.value = false
                        }
                        .subscribe({ items ->
                            listItem.value = items.results
                            listItem.value?.size?.let { onLoadSuccess(page) }
                        }, { e -> onLoadFail(e) }))
    }
}
