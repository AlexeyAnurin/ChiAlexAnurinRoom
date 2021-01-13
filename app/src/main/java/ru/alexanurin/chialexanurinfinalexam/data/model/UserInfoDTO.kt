package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfoDTO(
    @Json(name = "status")
    val status: Boolean? = null,
    @Json(name = "data")
    val userInfo: UserInfo
)

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "user")
    val user: User
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "username")
    val userName: String,
    @Json(name = "email")
    val email: String
)