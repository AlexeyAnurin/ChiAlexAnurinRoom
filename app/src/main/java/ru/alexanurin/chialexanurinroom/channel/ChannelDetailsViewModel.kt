package ru.alexanurin.chialexanurinroom.channel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexanurin.chialexanurinroom.rest.Channel
import ru.alexanurin.chialexanurinroom.rest.ChannelDetailsDto
import ru.alexanurin.chialexanurinroom.rest.ChannelsRepository
import ru.alexanurin.chialexanurinroom.rest.Data

class ChannelDetailsViewModel(private val channelDetailsId: Int, private val channelsRepository: ChannelsRepository): ViewModel() {

    var getChannelDetailsEvent = MutableLiveData<ChannelDetailsDto>()

    fun getChannelDetails() {
        viewModelScope.launch {
            val result = channelsRepository.getChannelDetails(channelDetailsId)
            getChannelDetailsEvent.postValue(result)
        }
    }
}