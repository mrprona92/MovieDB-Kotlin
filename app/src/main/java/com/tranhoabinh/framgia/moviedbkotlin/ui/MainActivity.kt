package com.tranhoabinh.framgia.moviedbkotlin.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.tranhoabinh.framgia.moviedbkotlin.R
import com.tranhoabinh.framgia.moviedbkotlin.core.BaseActivity
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getLayout(): Int = R.layout.activity_main
    override fun initComponent(savedInstanceState: Bundle?) {

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.app_name, R.string.app_name)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        navigation_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_movie_list -> replaceFragment(ListMovieFragment.newInstance(),
                        R.id.container)
                R.id.action_movie_favorite -> replaceFragment(ListMovieFragment.newInstance(),
                        R.id.container)
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
        replaceFragment(ListMovieFragment.newInstance(),
                R.id.container)
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(navigation_view)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    }
}
