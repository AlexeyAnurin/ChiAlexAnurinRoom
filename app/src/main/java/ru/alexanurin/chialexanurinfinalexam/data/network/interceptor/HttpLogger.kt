package ru.alexanurin.chialexanurinfinalexam.data.network.interceptor

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

internal class HttpLogger : HttpLoggingInterceptor.Logger {

    override fun log(message: String) {
        Log.d("tag", message)
    }
}