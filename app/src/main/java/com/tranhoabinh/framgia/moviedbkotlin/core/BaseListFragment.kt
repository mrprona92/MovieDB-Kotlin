package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.databinding.ViewDataBinding
import com.tranhoabinh.framgia.moviedbkotlin.BR
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.utils.DialogUtils

abstract class BaseListFragment<View : ViewDataBinding, ViewModel : BaseListViewModel<T>, T> : BaseFragment<View, ViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_list_item

    open fun showErrorToast(message: String) {
        DialogUtils.showErrorToast(context, message)
    }
}