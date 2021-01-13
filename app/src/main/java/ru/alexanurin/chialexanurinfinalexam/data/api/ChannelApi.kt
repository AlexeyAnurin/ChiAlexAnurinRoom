package ru.alexanurin.chialexanurinfinalexam.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelDetailsDto
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelDto

interface ChannelApi {
    //  запрос списка каналов.
    @GET("api/get-channels/")
    suspend fun getChannels(
    ): ChannelDto

    //  Запрос всех видео с подробной информацией из выбранного канала.
    @GET("api-mobile/search")
    suspend fun getChannelDetails(
       // @Query("limit") limit: Int = 5,
        @Query("channel") channelId: Int
    ): ChannelDetailsDto

}