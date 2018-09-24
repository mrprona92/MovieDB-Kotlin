package com.tranhoabinh.framgia.moviedbkotlin.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tranhoabinh.framgia.moviedbkotlin.BuildConfig
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.RemoteContract
import com.tranhoabinh.framgia.moviedbkotlin.data.remote.RemoteMovieService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
            GsonBuilder()
                    .setLenient()
                    .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
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


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(RemoteContract.BASE_API_LAYER)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideRemoteCurrencyService(retrofit: Retrofit): RemoteMovieService =
            retrofit.create(RemoteMovieService::class.java)

    @Provides
    @Singleton
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
}
