package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.quanda.moviedb.ui.base.BaseListFragment
import com.quanda.moviedb.ui.screen.movie.MovieListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentListItemBinding
import com.tranhoabinh.framgia.moviedbkotlin.ui.MainActivity
import com.tranhoabinh.framgia.moviedbkotlin.utils.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMovieFragment : BaseListFragment<FragmentListItemBinding, MovieListViewModel, Movie>(), ListMovieAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener {
    companion object {
        const val TAG = "ListMovieFragment"

        fun newInstance() = ListMovieFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<MovieListViewModel>()


    override fun initContent(viewBinding: FragmentListItemBinding) {
        viewBinding.viewModel = viewModel;
        viewModel.apply { initLoad() }
        viewBinding.apply {
            swipeRefresh.setOnRefreshListener(this@ListMovieFragment)
        }
        viewModel.apply {
            val listMovieAdapter = ListMovieAdapter(this@ListMovieFragment)
            val lineaLayoutManager = LinearLayoutManager(context)
            var endlessScrollListener: EndlessScrollListener = object : EndlessScrollListener(lineaLayoutManager) {
                override fun onLoadMore(current_page: Int) {
                    viewModel.loadMore(current_page)
                }
            }


            viewBinding.recyclerView.apply {
                layoutManager = lineaLayoutManager
                this.adapter = listMovieAdapter
                addOnScrollListener(endlessScrollListener)
            }
            listItem.observe(this@ListMovieFragment, Observer {
                when (isRefresh.value) {
                    true -> {
                        endlessScrollListener.resetIndex()
                        listMovieAdapter.refreshData(it)
                    }
                    false -> listMovieAdapter.updateData(it)
                }
            })
        }
        viewModel.isRefresh.observe(this, Observer {
            viewBinding.swipeRefresh.apply {
                isRefreshing = it == true
            }
        })
    }

    override fun onMovieClick(movie: Movie) {
        if (activity is MainActivity)
            (activity as MainActivity).apply {
                //TODO show details movie
            }
    }

    override fun onRefresh() {
        viewModel.refreshData()
    }
}