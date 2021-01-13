package ru.alexanurin.chialexanurinfinalexam.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelsDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChannels(channels: List<ChannelEntity>)

    @Query("SELECT * FROM $CHANNELS_TABLE_NAME")
    fun getChannels(): Flow<List<ChannelEntity>>
}

