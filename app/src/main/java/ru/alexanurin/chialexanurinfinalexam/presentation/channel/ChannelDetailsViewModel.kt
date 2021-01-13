package ru.alexanurin.chialexanurinfinalexam.presentation.channel

import androidx.lifecycle.MutableLiveData
import ru.alexanurin.chialexanurinfinalexam.data.model.ChannelDetailsDto
import ru.alexanurin.chialexanurinfinalexam.data.repo.ChannelsRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel

class ChannelDetailsViewModel(
    private val channelDetailsId: Int,
    private val channelsRepository: ChannelsRepository
) : BaseViewModel() {

    val getChannelDetailsEvent = MutableLiveData<ChannelDetailsDto>()

    init {
        launchErrorJob {
            val result = channelsRepository.getChannelDetails(channelDetailsId)
            getChannelDetailsEvent.postValue(result)
        }
    }
}