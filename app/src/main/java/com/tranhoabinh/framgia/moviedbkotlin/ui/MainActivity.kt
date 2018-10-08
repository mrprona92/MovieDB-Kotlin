package com.tranhoabinh.framgia.moviedbkotlin.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseActivity
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listfavorite.ListFavoriteFragment
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getLayout(): Int = R.layout.activity_main

    var toggle: ActionBarDrawerToggle? = null

    override fun initComponent(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        val drawer = drawer_layout as DrawerLayout
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name)
        drawer.addDrawerListener(toggle!!)
        toggle!!.syncState()

        navigation_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_movie_list -> {
                    replaceFragment(ListMovieFragment.newInstance(),
                            R.id.container, ListMovieFragment.TAG)
                }
                R.id.action_movie_favorite -> {
                    replaceFragment(ListFavoriteFragment.newInstance(),
                            R.id.container, ListFavoriteFragment.TAG)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        replaceFragment(ListMovieFragment.newInstance(),
                R.id.container, ListMovieFragment.TAG)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(navigation_view)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> super.onBackPressed()
        }
        return true
    }

    private var mToolBarNavigationListenerIsRegistered = false

    fun showBackButton(enable: Boolean) {
        if (enable) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            toggle?.isDrawerIndicatorEnabled = false
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            if (!mToolBarNavigationListenerIsRegistered) {
                toggle?.toolbarNavigationClickListener = View.OnClickListener { onBackPressed() }
                mToolBarNavigationListenerIsRegistered = true
            }
        } else {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            toggle?.isDrawerIndicatorEnabled = true
            toggle?.toolbarNavigationClickListener = null
            mToolBarNavigationListenerIsRegistered = false
        }
    }
}
