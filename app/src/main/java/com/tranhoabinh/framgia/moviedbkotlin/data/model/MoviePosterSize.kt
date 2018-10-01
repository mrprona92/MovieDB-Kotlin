package com.tranhoabinh.framgia.moviedbkotlin.data.model

import androidx.annotation.StringDef
import androidx.databinding.BaseObservable



class MoviePosterSize : BaseObservable() {

    companion object {
        const val SIZE_1 = "w92"
        const val SIZE_2 = "w154"
        const val SIZE_3 = "w185"
        const val SIZE_4 = "w342"
        const val SIZE_5 = "w500"
        const val SIZE_6 = "w780"
        const val ORIGINAL = "original"
    }

    @StringDef(
        SIZE_1, SIZE_2, SIZE_3, SIZE_4,
        SIZE_5, SIZE_6, ORIGINAL
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class Def
}