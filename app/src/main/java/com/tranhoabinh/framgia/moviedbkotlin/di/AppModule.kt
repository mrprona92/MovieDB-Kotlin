package com.tranhoabinh.framgia.moviedbkotlin.di

import android.content.res.Resources
import org.koin.dsl.module.module

val appModule = module(override = true) {
    single { createResources(get()) }
}

fun createResources(application: MovieDBApplication): Resources = application.resources
