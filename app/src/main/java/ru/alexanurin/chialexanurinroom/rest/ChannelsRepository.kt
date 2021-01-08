package ru.alexanurin.chialexanurinroom.rest


import com.squareup.moshi.Moshi

class ChannelsRepository{

    //  список каналов.
    suspend fun getChannels(): ChannelDto {
        return screenLifeApi.getChannels()
    }

    //  Подробная информация о каналах.
    suspend fun getChannelDetails(channelId: Int): ChannelDetailsDto {
        return screenLifeApi.getChannelDetails(channelId = channelId)
    }
}