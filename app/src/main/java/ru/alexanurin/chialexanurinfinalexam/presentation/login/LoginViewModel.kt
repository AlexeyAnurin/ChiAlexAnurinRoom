package ru.alexanurin.chialexanurinfinalexam.presentation.login

import ru.alexanurin.chialexanurinfinalexam.presentation.BaseViewModel
import ru.alexanurin.chialexanurinfinalexam.util.SingleLiveEvent
import ru.alexanurin.chialexanurinfinalexam.data.model.LoginUser
import ru.alexanurin.chialexanurinfinalexam.data.repo.AuthRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.LoginUserModel

class LoginViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    val toChannelScreenEvent = SingleLiveEvent<Unit>()

    //  когда токен попадёт в pref, то событие SingleLiveEvent приведёт к переходу на ChannelFragment.
    fun loginUser(loginUser: LoginUserModel) {
        launchErrorJob {
            authRepository.loginUser(loginUser)
            toChannelScreenEvent.call()
        }
    }
}