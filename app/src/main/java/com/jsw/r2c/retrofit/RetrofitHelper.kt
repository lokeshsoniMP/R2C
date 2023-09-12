package com.jsw.r2c.retrofit

import com.jsw.r2c.base.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {



    fun <T> getInstance(apiInterface: Class<T>): T {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()
        val retrofit: Retrofit = Retrofit.Builder().client(
            OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .build()
        ).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(apiInterface)
    }

}