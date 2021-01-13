package ru.alexanurin.chialexanurinfinalexam.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.alexanurin.chialexanurinfinalexam.data.database.ChannelEntity

@JsonClass(generateAdapter = true)
data class ChannelDto(

    @Json(name = "status")
    val status: Boolean? = null,
    @Json(name = "data")
    val dataList: List<Channel> = listOf()
)

@JsonClass(generateAdapter = true)
data class Channel(
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "id")
    val channelId: Int = 0
)

fun Channel.toEntity(): ChannelEntity {
    return ChannelEntity(title = title ?: "", channelId = channelId)
}




