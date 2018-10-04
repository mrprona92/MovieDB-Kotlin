package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listfavorite

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
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieAdapter
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail.MovieDetailFragment
import com.tranhoabinh.framgia.moviedbkotlin.utils.EndlessScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFavoriteFragment : BaseListFragment<FragmentListItemBinding, ListFavoriteViewModel, Movie>(), BaseRecyclerAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener {

    companion object {
        const val TAG = "ListFavoriteFragment"

        fun newInstance() = ListFavoriteFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<ListFavoriteViewModel>()

    override fun initContent(viewBinding: FragmentListItemBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.swipeRefresh.setOnRefreshListener(this@ListFavoriteFragment)
        viewModel.apply {
            initLoad()
            val listMovieAdapter = ListMovieAdapter(this@ListFavoriteFragment)
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
            listItem.observe(this@ListFavoriteFragment, Observer {
                when (isRefresh.value) {
                    true -> {
                        endlessScrollListener.resetIndex()
                        listMovieAdapter.refreshData(it)
                    }
                    false -> {
                        val listAdd = viewModel.listAdd.value ?: ArrayList()
                        if (!listAdd.isEmpty()) {
                            listMovieAdapter.updateData(viewModel.listAdd.value?.toMutableList()
                                    ?: mutableListOf())
                        }
                    }
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
        reloadData()
    }

    open fun reloadData() {
        viewModel.refreshData()
    }
}