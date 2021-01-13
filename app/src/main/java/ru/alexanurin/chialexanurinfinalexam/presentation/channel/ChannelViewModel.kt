package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import androidx.lifecycle.*
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelModel
import ru.alexanurin.chialexanurinfinalexam.data.repo.ChannelsRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel

class ChannelViewModel(private val channelsRepository: ChannelsRepository) : BaseViewModel() {

    //  Преобразовать списко каналов типа Flow в LiveData и отобразить список во фрагменте.
    val getChannelsEvent: LiveData<List<ChannelModel>> =
        channelsRepository.getChannelsFromBD().asLiveData()

    //  Сразу при создании ChannelViewModel выполнить запрос на получение списка каналов и
    // помещение их в БД.
    init {
        launchErrorJob {
            channelsRepository.getChannels()
        }
    }
}