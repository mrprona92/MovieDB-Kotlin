package com.tranhoabinh.framgia.moviedbkotlin.utils

import android.content.Context
import android.widget.Toast

object DialogUtils {
    open fun showErrorToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
