package com.pagingsample.core.repository.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pagingsample.core.repository.ForeSquareResponseTypeAdapter
import com.pagingsample.pages.home.repo.api.HomeApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val storesApiModule = module {


    single<Retrofit> {
        val httpUrl = HttpUrl.parse(HomeApi.BASE_URL)!!

        Retrofit.Builder()
            .baseUrl(httpUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<HomeApi> {
        val retrofit: Retrofit = get()
        retrofit.create(HomeApi::class.java)
    }

    single<Interceptor>("logger") {
        val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("API", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BODY
        logger
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get("logger"))
            .build()
    }

    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(List::class.java, ForeSquareResponseTypeAdapter())
            .create()
    }
}