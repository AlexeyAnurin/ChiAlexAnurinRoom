package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.LoginUserModel
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.UserModel

@JsonClass(generateAdapter = true)
data class LoginUser(
    @Json(name = "email")
    var email: String = "",
    @Json(name = "password")
    var password: String = "",
    @Json(name = "device_token")
    val deviceToken: String,
    @Json(name = "os_type")
    val osType: String = "android"
)

fun LoginUserModel.toDto(deviceToken: String): LoginUser {
    return LoginUser(
        email = email,
        password = password,
        deviceToken = deviceToken
    )
}