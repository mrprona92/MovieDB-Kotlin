package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.lifecycle.MutableLiveData

abstract class BaseListViewModel<T> : BaseViewModel() {

    var currentPage = MutableLiveData<Int>().apply { value = 0 }
    val items = MutableLiveData<ArrayList<T>>()
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    fun isFirstLoading() = currentPage.value == 0
            && (items.value == null || items.value?.size == 0)

    fun initLoad() {
        if (isFirstLoading()) {
            isLoading.value = true
            requestData(1)
        }
    }

    abstract fun requestData(page: Int)

    fun loadMore() {
        currentPage.value?.plus(1)?.let { requestData(it) }
    }

    fun onLoadSuccess(listSize: Int) {
        isLoading.value = false
    }

    override fun onLoadFail(e: Throwable) {
        super.onLoadFail(e)
        isLoadMore.value = false
    }
}