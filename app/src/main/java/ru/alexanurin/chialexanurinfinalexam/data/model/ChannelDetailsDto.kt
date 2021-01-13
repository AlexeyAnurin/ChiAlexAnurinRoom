package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChannelDetailsDto(
    @Json(name = "status")
    val status: Boolean? = null,
    @Json(name = "data")
    val channelData: ChannelData
)

@JsonClass(generateAdapter = true)
data class ChannelData(
    @Json(name = "video")
    val video: Video
)

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "count")
    val count: Int,
    @Json(name = "data")
    val data: List<Data>
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "count_likes")
    val countLikes: Int
)



