package com.tranhoabinh.framgia.moviedbkotlin.data.remote.response

import com.google.gson.annotations.SerializedName
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie

open class GetMoviesResponse(
        @SerializedName("page") val page: Int? = null,
        @SerializedName("total_results") val totalResults: Int? = null,
        @SerializedName("total_pages") val totalPages: Int? = null,
        @SerializedName("results") val results: ArrayList<Movie>? = null
) : BaseResponse()