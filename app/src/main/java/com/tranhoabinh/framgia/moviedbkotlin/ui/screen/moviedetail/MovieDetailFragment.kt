package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseFragment
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>(), SwipeRefreshLayout.OnRefreshListener, MovieDetailViewModel.OnItemClick {
    companion object {
        const val TAG = "MovieDetailFragment"
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


    override fun initContent(viewBinding: FragmentMovieDetailBinding) {
        viewBinding.viewModel = viewModel
        movieId = arguments?.getString(MOVIE_ID) ?: ""
        viewModel.apply {
            requestMovieDetail(movieId)
            requestCheckFavorite(movieId)
        }
    }

    override fun onRefresh() {
    }

    override fun onFavoriteClick() {
        viewModel.handleFavoriteClick(movieId)
    }
}