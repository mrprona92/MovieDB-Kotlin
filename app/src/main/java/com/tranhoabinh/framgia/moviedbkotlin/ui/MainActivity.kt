package com.tranhoabinh.framgia.moviedbkotlin.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseActivity
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieFragment

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getLayout(): Int = R.layout.activity_main
    override fun initComponent(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        replaceFragment(ListMovieFragment.newInstance(),
                R.id.container)
    }
}
