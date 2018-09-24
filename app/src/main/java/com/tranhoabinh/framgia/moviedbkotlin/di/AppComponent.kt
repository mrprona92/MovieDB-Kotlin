package com.tranhoabinh.framgia.moviedbkotlin.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [(AppModule::class), (RoomModule::class), (RemoteModule::class)])
@Singleton
interface AppComponent {

}
