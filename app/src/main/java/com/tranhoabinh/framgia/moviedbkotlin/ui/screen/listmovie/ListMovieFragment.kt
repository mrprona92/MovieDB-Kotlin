package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseListFragment
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseRecyclerAdapter
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentListItemBinding
import com.tranhoabinh.framgia.moviedbkotlin.ui.MainActivity
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail.MovieDetailFragment
import com.tranhoabinh.framgia.moviedbkotlin.utils.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListMovieFragment : BaseListFragment<FragmentListItemBinding, ListMovieViewModel, Movie>(), BaseRecyclerAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener {
    companion object {
        const val TAG = "ListMovieFragment"

        fun newInstance() = ListMovieFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<ListMovieViewModel>()


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
                override fun onLoadMore(currentPage: Int) {
                    viewModel.loadMore(currentPage)
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

        viewModel.errorMessage.observe(this, Observer {
            showErrorToast(it)
        })
    }

    override fun onMovieClick(movie: Movie) {
        if (activity is MainActivity)
            (activity as MainActivity).apply {
                val movieDetailFragment = MovieDetailFragment.newInstance(movie.id)
                replaceFragmentAddToBackStack(movieDetailFragment,
                        R.id.container, MovieDetailFragment.TAG)
            }
    }

    override fun onRefresh() {
        viewModel.refreshData()
    }
}