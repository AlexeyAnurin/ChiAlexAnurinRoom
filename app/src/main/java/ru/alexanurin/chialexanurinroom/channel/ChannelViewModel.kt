package ru.alexanurin.chialexanurinroom.channel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.alexanurin.chialexanurinroom.rest.Channel
import ru.alexanurin.chialexanurinroom.rest.ChannelsRepository

class ChannelViewModel(private val channelsRepository: ChannelsRepository) : ViewModel() {

    var getChannelsEvent = MutableLiveData<List<Channel>>()

    fun getChannels() {
        viewModelScope.launch {
            val result = channelsRepository.getChannels()
            getChannelsEvent.value = result.dataList!!
        }
    }
}
