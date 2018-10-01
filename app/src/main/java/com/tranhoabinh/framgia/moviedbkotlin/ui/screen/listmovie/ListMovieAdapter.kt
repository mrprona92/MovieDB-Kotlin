package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseRecyclerAdapter
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.databinding.ItemMoviedbBinding

class ListMovieAdapter(private val listener: OnItemClick)
    : BaseRecyclerAdapter<Movie>() {


    override fun createBinding(parent: ViewGroup, viewType: Int?): ViewDataBinding {
        val viewDataBinding = DataBindingUtil.inflate<ItemMoviedbBinding>(
                LayoutInflater.from(parent.context),
                getLayoutRes(),
                parent, false)
        viewDataBinding.onClick = listener
        return viewDataBinding
    }


    override fun bind(binding: ViewDataBinding, item: Movie) {
        if (binding is ItemMoviedbBinding) {
            binding.item = item
        }
    }

    interface OnItemClick {
        fun onMovieClick(movie: Movie)
    }

    override fun handleClick(data: Movie) {
        listener.onMovieClick(data)
    }

    private fun getLayoutRes(): Int {
        return R.layout.item_moviedb
    }

    fun updateData(dataList: MutableList<Movie>) {
        items.addAll(dataList)
        notifyItemRangeChanged(dataList.size, items.size)
    }

    fun refreshData(dataList: MutableList<Movie>) {
        items.clear()
        items.addAll(dataList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}