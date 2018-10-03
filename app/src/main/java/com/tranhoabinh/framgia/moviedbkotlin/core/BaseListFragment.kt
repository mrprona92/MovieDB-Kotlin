package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.databinding.ViewDataBinding
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R

abstract class BaseListFragment<View : ViewDataBinding, ViewModel : BaseListViewModel<T>, T> : BaseFragment<View, ViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_list_item
}