package com.tranhoabinh.framgia.moviedbkotlin.utils

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tranhoabinh.framgia.moviedbkotlin.data.model.MoviePosterSize


object Constant {
    const val THRESHOLD_CLICK_TIME = 1200
    const val IMAGE_URL = "https://image.tmdb.org/t/p/"
}

@BindingAdapter(value = ["loadImage", "placeholder", "fitCenter", "centerCrop"],
        requireAll = false)
fun ImageView.setImageUrl(url: String? = "", placeholder: Drawable?, fitCenter: Boolean?, centerCrop: Boolean?) {
    val options = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE)
    if (TextUtils.isEmpty(url)) {
        setImageDrawable(placeholder)
    } else {
        fitCenter?.apply { if (fitCenter) options.fitCenter() }
        centerCrop?.apply { if (centerCrop) options.centerCrop() }
        val imageUrl: String = Constant.IMAGE_URL + MoviePosterSize.SIZE_5 + "/" + url
        Glide.with(context).load(imageUrl).apply(options).into(this)
    }
}

@BindingAdapter("clickSafe")
fun setClickSafe(view: View, listener: View.OnClickListener?) {
    view.setOnClickListener(object : View.OnClickListener {
        private var mLastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < Constant.THRESHOLD_CLICK_TIME) {
                return
            }
            listener?.onClick(v)
            mLastClickTime = SystemClock.elapsedRealtime()
        }
    })
}


