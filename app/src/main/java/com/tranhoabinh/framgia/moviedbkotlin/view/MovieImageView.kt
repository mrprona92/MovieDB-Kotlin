package com.tranhoabinh.framgia.moviedbkotlin.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created by tran.hoa.binh on 27/09/2018.
 */
class MovieImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    companion object {
        private const val ASPECT_RATIO = 0.5f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = Math.round(width * ASPECT_RATIO)
        setMeasuredDimension(width, height)
    }
}