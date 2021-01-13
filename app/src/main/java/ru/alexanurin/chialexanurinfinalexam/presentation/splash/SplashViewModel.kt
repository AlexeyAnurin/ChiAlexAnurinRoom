package ru.alexanurin.chialexanurinfinalexam.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.alexanurin.chialexanurinfinalexam.data.repo.AuthRepository
import ru.alexanurin.chialexanurinfinalexam.util.SingleLiveEvent

class SplashViewModel(private val authRepository: AuthRepository) : ViewModel() {

    val toChannelScreenEvent = SingleLiveEvent<Unit>()
    val toLogInScreenEvent = SingleLiveEvent<Unit>()

    init {
        viewModelScope.launch {
            delay(2000)
            if (authRepository.isAuthorized()) {
                toChannelScreenEvent.call()
            } else toLogInScreenEvent.call()
        }
    }
}