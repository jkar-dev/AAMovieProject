package com.jkapps.aamovieproject.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "14bb2449116aeea6b3c3661a4d68c4bc"

object NetworkModule {

    private val apiKeyInterceptor = Interceptor {
        val original = it.request()
        val originalUrl = original.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val request = original.newBuilder().url(url).build()
        it.proceed(request)
    }

    private val logginingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(logginingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val tmdbService = retrofit.create<TmdbApiService>()
}