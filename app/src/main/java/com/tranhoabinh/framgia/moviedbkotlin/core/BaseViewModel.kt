package com.tranhoabinh.framgia.moviedbkotlin.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply { value = false }

    val errorMessage = MutableLiveData<String>()

    val compositeDisposable = CompositeDisposable()


    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun showError(e: Throwable) {
        errorMessage.value = e.message
    }

    fun onActivityDestroyed() {
        compositeDisposable.clear()
    }


    open fun onLoadFail(e: Throwable) {
        isLoading.value = false
        showError(e)
    }

    open fun onLoadSuccess(page: Int) {
        isLoading.value = false
    }

}