package com.quanda.moviedb.ui.screen.movie

import com.tranhoabinh.framgia.moviedbkotlin.core.BaseListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository


class MovieListViewModel constructor(
        private val repository: Repository
) : BaseListViewModel<Movie>() {

    override fun requestData(page: Int) {
        repository.getMovieList(page)
                .subscribe({
                 //TODO apply response
                    onLoadSuccess(it.results?.size ?: 0)
                }, {
                    onLoadFail(it)
                })
    }

}