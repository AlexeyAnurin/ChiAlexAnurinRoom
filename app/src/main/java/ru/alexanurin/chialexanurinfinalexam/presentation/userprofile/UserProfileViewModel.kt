package ru.alexanurin.chialexanurinfinalexam.presentation.userprofile

import androidx.lifecycle.MutableLiveData
import ru.alexanurin.chialexanurinfinalexam.data.model.UserInfoDTO
import ru.alexanurin.chialexanurinfinalexam.data.repo.AuthRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel
import ru.alexanurin.chialexanurinfinalexam.util.SingleLiveEvent

class UserProfileViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    val getUserInfoEvent = MutableLiveData<UserInfoDTO>()
    val toLoginScreenEvent = SingleLiveEvent<Unit>()

    init {
        launchErrorJob {
            val result = authRepository.getUserInfo()
            getUserInfoEvent.postValue(result)
        }
    }

    fun logOut() {
        launchErrorJob {
            authRepository.logOut()
            toLoginScreenEvent.call()
        }
    }
}