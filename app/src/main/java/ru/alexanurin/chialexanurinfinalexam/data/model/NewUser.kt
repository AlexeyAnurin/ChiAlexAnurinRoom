package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.UserModel

@JsonClass(generateAdapter = true)
data class NewUser(
    @Json(name = "email")
    val email: String,
    @Json(name = "username")
    val userName: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "device_token")
    val deviceToken: String,
    @Json(name = "os_type")
    val osType: String = "android"
)

fun UserModel.toDto(deviceToken: String): NewUser {
    return NewUser(
        email = email,
        userName = userName,
        password = password,
        deviceToken = deviceToken
    )
}