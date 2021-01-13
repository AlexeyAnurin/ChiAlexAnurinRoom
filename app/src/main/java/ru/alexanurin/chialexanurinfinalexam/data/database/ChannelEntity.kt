package ru.alexanurin.chialexanurinfinalexam.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelModel

const val CHANNELS_TABLE_NAME = "Channels"

@Entity(tableName = CHANNELS_TABLE_NAME)
data class ChannelEntity(
    @PrimaryKey(autoGenerate = true)
    val channelId: Int,
    val title: String
)

fun ChannelEntity.toUI():ChannelModel{
    return ChannelModel(channelId = channelId, title = title)
}

//  функция-расширение
fun List<ChannelEntity>.toUI() = map { it.toUI() }
