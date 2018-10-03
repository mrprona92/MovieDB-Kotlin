package com.tranhoabinh.framgia.moviedbkotlin.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tranhoabinh.framgia.moviedbkotlin.BuildConfig
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.RemoteContract
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.RemoteMovieService
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.Repository
import com.tranhoabinh.framgia.moviedbkotlin.data.repository.RepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module(override = true) {
    single { createGson() }
    single { createOkHttpClient() }
    single { createHeaderInterceptor() }
    single { createRetrofit(get(), get()) }
    single { createRemoteMovieService(get()) }
    single<Repository> { RepositoryImpl(get(), get()) }
}


fun createGson(): Gson =
        GsonBuilder()
                .setLenient()
                .create()

fun createOkHttpClient(): OkHttpClient {
    val level: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(level))
            .addInterceptor(createHeaderInterceptor())
            .build()
}

fun createHeaderInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request()
        val newUrl = request.url().newBuilder()
                .addQueryParameter("api_key", RemoteContract.ACCESS_KEY)
                .build()
        val newRequest = request.newBuilder()
                .url(newUrl)
                .header("Content-Type", "application/json")
                .method(request.method(), request.body())
                .build()
        chain.proceed(newRequest)
    }
}

fun createRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
                .baseUrl(RemoteContract.BASE_API_LAYER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

fun createRemoteMovieService(retrofit: Retrofit): RemoteMovieService =
        retrofit.create(RemoteMovieService::class.java)

