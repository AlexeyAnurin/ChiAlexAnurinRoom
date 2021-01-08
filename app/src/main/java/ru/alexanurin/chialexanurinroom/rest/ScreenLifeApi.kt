package ru.alexanurin.chialexanurinroom.rest

import retrofit2.http.GET
import retrofit2.http.Query

//   interface holds the API call
interface ScreenLifeApi {

    //  запрос списка каналов.
    @GET("api/get-channels/")
    suspend fun getChannels(
    ): ChannelDto

    //  Запрос первых пяти видео с подробной информацией из выбранного канала.
    @GET("api-mobile/search")
    suspend fun getChannelDetails(
        @Query("limit") limit: Int = 5,
        @Query("channel") channelId: Int
    ): ChannelDetailsDto
}