package com.tranhoabinh.framgia.moviedbkotlin.core


import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<ViewDataBinding>>() {

    var items: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewDataBinding> {
        return BaseViewHolder(createBinding(parent = parent, viewType = viewType))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int? = 0): ViewDataBinding

    protected abstract fun bind(binding: ViewDataBinding, item: T)

    abstract fun handleClick(data: T)

    interface OnItemClick {
        fun onMovieClick(movie: Movie)
    }

    private fun getItem(position: Int): T {
        return items[position]
    }
}
