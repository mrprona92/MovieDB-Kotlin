package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}