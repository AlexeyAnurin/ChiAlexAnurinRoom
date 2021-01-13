package ru.alexanurin.chialexanurinfinalexam.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelsDao {
    //  Поместить список каналов в с сервера в БД.
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannels(channels: List<ChannelEntity>)

    //  Получить список каналов из БД для отображения.
    @Query("SELECT * FROM $CHANNELS_TABLE_NAME")
    fun getChannels(): Flow<List<ChannelEntity>>
}

