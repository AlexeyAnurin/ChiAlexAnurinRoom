package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserLogOut(
    @Json(name = "device_token")
    val deviceToken: String,
    @Json(name = "os_type")
    val osType: String = "android"
)