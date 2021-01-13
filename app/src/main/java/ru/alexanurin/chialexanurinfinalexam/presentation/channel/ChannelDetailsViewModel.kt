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
            //  При создании ChannelDetailsViewModel отправить запрос на получение подробной информации о выбранном канале по его Id.
            val result = channelsRepository.getChannelDetails(channelDetailsId)
            //  Поместить ответ от серва в контейнер LiveData и отобразить на UI ChannelDetailsFragment.
            getChannelDetailsEvent.postValue(result)
        }
    }
}