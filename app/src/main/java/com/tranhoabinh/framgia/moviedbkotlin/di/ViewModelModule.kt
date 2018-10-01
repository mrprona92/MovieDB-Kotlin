package com.tranhoabinh.framgia.moviedbkotlin.di

import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.MovieListViewModel
import com.tranhoabinh.framgia.moviedbkotlin.ui.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module(override = true) {
    viewModel { MainActivityViewModel() }
    viewModel { MovieListViewModel(get()) }
}