package com.tranhoabinh.framgia.moviedbkotlin.di

import com.tranhoabinh.framgia.moviedbkotlin.ui.MainActivityViewModel
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.listmovie.ListMovieViewModel
import com.tranhoabinh.framgia.moviedbkotlin.ui.screen.moviedetail.MovieDetailViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module(override = true) {
    viewModel { MainActivityViewModel() }
    viewModel { ListMovieViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}