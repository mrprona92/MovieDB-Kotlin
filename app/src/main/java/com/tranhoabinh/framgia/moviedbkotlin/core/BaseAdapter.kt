package com.tranhoabinh.framgia.moviedbkotlin.core


import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<ViewDataBinding>>() {

    var items: MutableList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewDataBinding> {
        return BaseViewHolder(createBinding(parent = parent, viewType = viewType)).listen { pos, type ->
            val item = items.get(pos)
            handleClick(item)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int? = 0): ViewDataBinding

    protected abstract fun bind(binding: ViewDataBinding, item: T)

    abstract fun handleClick(data: T)

    private fun getItem(position: Int): T {
        return items[position]
    }
}
