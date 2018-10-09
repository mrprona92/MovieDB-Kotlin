package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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

    private var endlessScrollListener: EndlessScrollListener? = null

    override fun initContent(viewBinding: FragmentListItemBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.swipeRefresh.setOnRefreshListener(this@ListMovieFragment)
        viewModel.apply {
            val listMovieAdapter = ListMovieAdapter(this@ListMovieFragment)
            val gridLayoutManager = GridLayoutManager(context, 2)

            viewBinding.recyclerView.apply {
                layoutManager = gridLayoutManager
                adapter = listMovieAdapter
            }

            endlessScrollListener = object : EndlessScrollListener(gridLayoutManager) {
                override fun onLoadMore(currentPage: Int) {
                    isLoadMore.value = true
                    viewModel.loadMore(currentPage)
                }
            }
            viewBinding.recyclerView.addOnScrollListener(endlessScrollListener as EndlessScrollListener)

            if (!isBackFromDetail) {
                initLoad()
            } else {
                (endlessScrollListener as EndlessScrollListener).restoreIndex(viewModel.currentPage.value
                        ?: 1)
            }

            listItem.observe(this@ListMovieFragment, Observer {
                if (!isBackFromDetail) {
                    when (isRefresh.value) {
                        true -> {
                            listMovieAdapter.refreshData(it)
                            listItemBackup.clear()
                            listItemBackup.addAll(it)
                        }
                        false -> {
                            listMovieAdapter.updateData(it)
                            listItemBackup.addAll(it)
                        }
                    }
                } else {
                    //When back from detail screen reload with data backup
                    listMovieAdapter.updateData(listItemBackup)
                    isBackFromDetail = false
                }
            })

            isRefresh.observe(this@ListMovieFragment, Observer {
                viewBinding.swipeRefresh.apply {
                    isRefreshing = it == true
                }
            })

            isLoadFailed.observe(this@ListMovieFragment, Observer {
                when (isLoadFailed.value) {
                    true -> {
                        (endlessScrollListener as EndlessScrollListener).apply {
                            restoreIndex(viewModel.currentPage.value ?: 1)
                        }
                    }
                }
            })
        }
        activity?.title = tag
    }

    override fun onMovieClick(movie: Movie) {
        if (activity is MainActivity)
            (activity as MainActivity).apply {
                val movieDetailFragment = MovieDetailFragment.newInstance(movie.id)
                replaceFragmentAddToBackStack(movieDetailFragment,
                        R.id.container, MovieDetailFragment.TAG)
                isBackFromDetail = true
            }
    }

    override fun onRefresh() {
        viewModel.refreshData()
        endlessScrollListener?.resetIndex()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.listItem.removeObservers(this)
        viewModel.isLoading.removeObservers(this)
        viewModel.isRefresh.removeObservers(this)
    }
}