package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)

fun <T : RecyclerView.ViewHolder> T.listen(OnItemClick: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        OnItemClick.invoke(adapterPosition, itemViewType)
    }
    return this
}