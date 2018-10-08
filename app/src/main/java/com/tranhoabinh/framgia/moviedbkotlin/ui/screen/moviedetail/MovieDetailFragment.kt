package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseFragment
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentMovieDetailBinding
import com.tranhoabinh.framgia.moviedbkotlin.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>(), SwipeRefreshLayout.OnRefreshListener, MovieDetailViewModel.OnItemClick {
    companion object {
        const val TAG = "Movie Detail Fragment"
        const val MOVIE_ID = "movieId"

        fun newInstance(movieId: String): MovieDetailFragment {
            val args = Bundle()
            args.putString(MOVIE_ID, movieId)
            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var movieId: String = ""

    override val viewModel by viewModel<MovieDetailViewModel>()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_movie_detail

    var mainActivity: MainActivity? = null


    override fun initContent(viewBinding: FragmentMovieDetailBinding) {
        viewBinding.viewModel = viewModel
        viewBinding.onClick = this@MovieDetailFragment
        viewBinding.swipeRefresh.setOnRefreshListener(this@MovieDetailFragment)
        movieId = arguments?.getString(MOVIE_ID) ?: ""

        viewModel.apply {
            requestMovieDetail(movieId)
            isRefresh.observe(this@MovieDetailFragment, Observer {
                viewBinding.swipeRefresh.apply {
                    isRefreshing = it == true
                }
            })
            isFavoriteChanged.observe(this@MovieDetailFragment, Observer {
                when (it) {
                    true -> showToast(context?.getString(R.string.list_favorite_movie_added))
                    false -> showToast(context?.getString(R.string.list_favorite_movie_remove))
                }
            })
            title.observe(this@MovieDetailFragment, Observer {
                activity?.apply {
                    title = it
                }
            })
        }
        mainActivity = activity as MainActivity
        mainActivity?.showBackButton(true)
    }

    override fun onRefresh() {
        viewModel.apply {
            requestMovieDetail(movieId)
        }
    }

    override fun onFavoriteClick() {
        viewModel.handleFavoriteClick(movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity?.showBackButton(false)
    }
}