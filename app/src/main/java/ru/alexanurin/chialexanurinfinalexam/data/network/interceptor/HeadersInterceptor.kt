package ru.alexanurin.chialexanurinfinalexam.data.network.interceptor

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class HeadersInterceptor(private val sharedPref: SharedPreferences) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        return chain.proceed(
            chain.request().newBuilder().apply {
                val token = sharedPref.getString(TOKEN, "")

                if (!token.isNullOrEmpty()) {
                    removeHeader(AUTHORIZATION)
                    addHeader(AUTHORIZATION, "$BEARER $token")
                }
            }.build()
        )
    }
    companion object {
        const val AUTHORIZATION = "Authorization"
        const val TOKEN = "TOKEN"
        const val USERID = "USERID"
        const val BEARER = "Bearer"
    }
}

