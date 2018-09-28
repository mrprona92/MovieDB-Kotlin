package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.lifecycle.MutableLiveData

abstract class BaseListViewModel<T> : BaseViewModel() {

    var currentPage = MutableLiveData<Int>().apply { value = 0 }
    val items = MutableLiveData<ArrayList<T>>()
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }
    val isRefresh = MutableLiveData<Boolean>().apply { value = false }

    val listItem = MutableLiveData<ArrayList<T>>()

    fun isFirstLoading() = currentPage.value == 0
            && (items.value == null || items.value?.size == 0)

    fun initLoad() {
        if (isFirstLoading()) {
            isLoading.value = true
            isLoadMore.value = false
            requestData(1)
        }
    }

    abstract fun requestData(page: Int)

    fun refreshData() {
        isLoading.value = true
        isRefresh.value = true
        isLoadMore.value = false
        currentPage.value = 1
        requestData(1)
    }

    fun loadMore(currentPage: Int) {
        this.currentPage.value = currentPage
        isLoadMore.value = true
        requestData(currentPage)
    }


    override fun onLoadSuccess() {
        isLoading.value = false
        isLoadMore.value = false
        isRefresh.value = false
    }
}