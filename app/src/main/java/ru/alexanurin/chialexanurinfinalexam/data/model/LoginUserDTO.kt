package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginUserDTO(
    @Json(name = "accessToken")
    val accessToken: String,
    @Json(name = "user")
    val loggedUserData: LoggedUserData
)

@JsonClass(generateAdapter = true)
data class LoggedUserData(
    @Json(name = "id")
    val id: Int = 0
)



