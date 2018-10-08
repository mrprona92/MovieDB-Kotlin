package com.tranhoabinh.framgia.moviedbkotlin.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndlessScrollListener(private val mLinearLayoutManager: GridLayoutManager,var loading: Boolean) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private val visibleThreshold = 8
    var firstVisibleItem: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {

            currentPage++

            onLoadMore(currentPage)

            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    fun restoreIndex(page: Int) {
        currentPage = page
        loading = false
    }

    fun resetIndex() {
        currentPage = 1
        totalItemCount = 0
        previousTotal = 0
        firstVisibleItem = 0
        visibleItemCount = 0
        loading = false
    }

    companion object {
        var TAG = EndlessScrollListener::class.java.simpleName
    }
}