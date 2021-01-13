package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewUserDTO(
    @Json(name = "status")
    val status: Boolean? = null,
    @Json(name = "data")
    val userData: UserData
)

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "id")
    val id: Int = 0
)



