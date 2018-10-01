package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseFragment
import com.tranhoabinh.framgia.moviedbkotlin.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>(), SwipeRefreshLayout.OnRefreshListener {

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

    override val viewModel by viewModel<MovieDetailViewModel>()

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_movie_detail


    override fun initContent(viewBinding: FragmentMovieDetailBinding) {
        viewBinding.viewModel = viewModel
        val movieId = arguments?.getString(MOVIE_ID)
        viewModel.apply { movieId?.let { requestMovieDetail(it) } }
    }

    override fun onRefresh() {
    }
}