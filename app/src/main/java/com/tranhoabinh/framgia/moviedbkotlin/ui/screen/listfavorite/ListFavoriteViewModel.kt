package com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listfavorite

import androidx.lifecycle.MutableLiveData
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.data.model.Movie
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import com.tranhoabinh.framgia.moviedbkotlin.data.room.RoomContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ListFavoriteViewModel constructor(
        private val repository: Repository
) : BaseListViewModel<Movie>() {

    val listAdd = MutableLiveData<ArrayList<Movie>>()

    override fun requestData(page: Int) {
        compositeDisposable.add(
                repository.getMovieFavorites(RoomContract.TABLE_MOVIE_NUMBER_PER_PAGE, (page.minus(1)) * RoomContract.TABLE_MOVIE_NUMBER_PER_PAGE)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally {
                            isLoading.value = false
                            isLoadMore.value = false
                        }
                        .subscribe({ items ->
                            if (items.isEmpty()) {
                                listAdd.value?.clear()
                                listItem.value?.clear()
                            } else {
                                if (currentPage.value == 1) listItem.value?.clear()
                                currentPage.value = page

                                listAdd.value = ArrayList(items)

                                listItem.value = ArrayList(items)
                                listItem.value?.size?.let { onLoadSuccess(page) }
                            }
                        }, { e -> onLoadFail(e) }))
    }
}
