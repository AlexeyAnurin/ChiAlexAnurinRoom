package ru.alexanurin.chialexanurinroom.rest

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChannelDto(

    @Json(name = "status")
    val status: Boolean? = null,
    @Json(name = "data")
    val dataList: List<Channel>? = null
)

@JsonClass(generateAdapter = true)
data class Channel(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "id")
    val channelId: Int = 0
)




