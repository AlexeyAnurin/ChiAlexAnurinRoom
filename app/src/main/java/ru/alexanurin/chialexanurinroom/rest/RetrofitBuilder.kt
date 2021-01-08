package ru.alexanurin.chialexanurinroom.rest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://api.screenlife.com/"

// interceptor -> Okhttp -> Retrofit.
fun retrofit(): Retrofit {
    return Retrofit.Builder().client(OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

val screenLifeApi: ScreenLifeApi = retrofit().create(ScreenLifeApi::class.java) //имплементация эндпоинтов
