package com.example.ravi.mvvmsample.api

import com.example.ravi.mvvmsample.helpers.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient{

    private val myOkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(getHttpLoggingInterceptor())
        .build()


    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(myOkHttpClient)
        .baseUrl(Constants.BASE_URL) // YOUR BASE URL OF API
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    private fun getHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }



}