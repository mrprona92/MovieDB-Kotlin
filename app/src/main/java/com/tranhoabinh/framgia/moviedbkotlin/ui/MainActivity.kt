package com.tranhoabinh.framgia.moviedbkotlin.ui

import android.os.Bundle
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseActivity
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieFragment

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getLayout(): Int = R.layout.activity_main
    override fun initComponent(savedInstanceState: Bundle?) {
        replaceFragment(ListMovieFragment.newInstance(),
                R.id.container)
    }
}
