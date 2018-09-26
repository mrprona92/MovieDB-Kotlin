package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import android.os.Bundle
import com.quanda.moviedb.ui.base.BaseListFragment
import com.quanda.moviedb.ui.screen.movie.MovieListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentListItemBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMovieFragment : BaseListFragment<FragmentListItemBinding, MovieListViewModel, Movie>() {

    companion object {
        const val TAG = "ListMovieFragment"

        fun newInstance() = ListMovieFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<MovieListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.run { initLoad() }

        //TODO apply data for recycler view
    }

}