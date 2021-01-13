package ru.alexanurin.chialexanurinfinalexam.presentation.login

import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.UserModel
import ru.alexanurin.chialexanurinfinalexam.data.repo.AuthRepository
import ru.alexanurin.chialexanurinfinalexam.util.SingleLiveEvent

class CreateNewUserViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    val toChannelScreenEvent = SingleLiveEvent<Unit>()

    fun createNewUser(newUser: UserModel) {
        launchErrorJob {
            authRepository.createNewUser(newUser)
            toChannelScreenEvent.call()
        }
    }
}