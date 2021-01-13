package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import androidx.lifecycle.*
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelModel
import ru.alexanurin.chialexanurinfinalexam.data.repo.ChannelsRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel

class ChannelViewModel(private val channelsRepository: ChannelsRepository) : BaseViewModel() {

    val getChannelsEvent: LiveData<List<ChannelModel>> =
        channelsRepository.getChannelsFromBD().asLiveData()

    init {
        launchErrorJob {
            // loadingEvent.postValue(true)
            channelsRepository.getChannels()
            //  loadingEvent.postValue(false)
        }
    }
}