package ru.alexanurin.chialexanurinfinalexam.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.alexanurin.chialexanurinfinalexam.data.api.ChannelApi
import ru.alexanurin.chialexanurinfinalexam.data.database.ChannelsDao
import ru.alexanurin.chialexanurinfinalexam.data.database.toUI
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelDetailsDto
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelModel
import ru.alexanurin.chialexanurinfinalexam.data.model.toEntity

class ChannelsRepository(
    private val channelApi: ChannelApi,
    private val channelsDao: ChannelsDao
) {
    //  список каналов. Получаем напрямую с серва.
    suspend fun getChannels() {
        withContext(Dispatchers.IO) {
            channelApi.getChannels()
            channelsDao.insertChannels(channelApi.getChannels().dataList.map { it.toEntity() })
        }
    }

    //  список каналов. из базы
    fun getChannelsFromBD(): Flow<List<ChannelModel>> {
        return channelsDao.getChannels().map { it.toUI() }
    }


    //  Подробная информация о каналах.
    suspend fun getChannelDetails(channelId: Int): ChannelDetailsDto {
        return channelApi.getChannelDetails(channelId = channelId)
    }
}