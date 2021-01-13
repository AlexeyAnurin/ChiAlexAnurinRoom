package ru.alexanurin.chialexanurinfinalexam.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.CurlLoggingInterceptor
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.HeadersInterceptor

const val BASE_URL = "https://api.screenlife.com/"

class RetrofitBuilder(private val headersInterceptor: HeadersInterceptor, private val curlLoggingInterceptor: CurlLoggingInterceptor) {

    // interceptor -> Okhttp -> Retrofit.
    fun retrofit(): Retrofit {
        return Retrofit.Builder().client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            addInterceptor(headersInterceptor)
            addNetworkInterceptor(curlLoggingInterceptor)
        }.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}