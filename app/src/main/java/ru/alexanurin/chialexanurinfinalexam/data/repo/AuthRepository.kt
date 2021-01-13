package ru.alexanurin.chialexanurinfinalexam.data.repo

import android.content.SharedPreferences
import android.util.Log
import com.fingerprintjs.android.fingerprint.Fingerprinter
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.UserModel
import ru.alexanurin.chialexanurinfinalexam.data.api.AuthApi
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.HeadersInterceptor
import ru.alexanurin.chialexanurinfinalexam.data.model.*
import ru.alexanurin.chialexanurinfinalexam.presentation.login.model.LoginUserModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepository(
    private val sharedPref: SharedPreferences,
    private val authApi: AuthApi,
    private val fingerprinter: Fingerprinter
) {

    //  проверка на наличие сохранённого токена.
    fun isAuthorized(): Boolean {
        return !sharedPref.getString(TOKEN, "").isNullOrEmpty()
    }
    //  Создание нового пользователя.
    suspend fun createNewUser(newUser: UserModel): NewUserDTO {
        val deviceToken = getDeviceToken()
        Log.d("tag", "deviceToken $deviceToken")
        return authApi.createNewUser(newUser.toDto(deviceToken))
    }

    private suspend fun getDeviceToken() = suspendCoroutine<String> {
        fingerprinter.getDeviceId { result ->
            val deviceId = result.deviceId
            sharedPref.edit().putString(DEVICE_ID, deviceId).apply()
            it.resume(deviceId)
        }
    }

    //  Вход в приложение.
    suspend fun loginUser(loginUserModel: LoginUserModel): LoginUserDTO {
        val deviceToken = getDeviceToken()
        val response = authApi.loginUser(loginUserModel.toDto(deviceToken))
        //  Из ответа от сервера взять токен для подписи запросов/повторного входа без
        // авторизации, и Id пользователя для доступа к его профилю.
        sharedPref.edit().putString(TOKEN, response.accessToken).apply()
        sharedPref.edit().putInt(USER_ID, response.loggedUserData.id).apply()
        println("sharedPref: ${sharedPref.getString(TOKEN, "")}") //L
        return response
    }

    suspend fun getUserInfo(): UserInfoDTO {
        return authApi.getUserInfo(sharedPref.getInt(USER_ID,0))
    }

    suspend fun logOut(){
        val deviceToken = sharedPref.getString(DEVICE_ID,"")
        Log.d("tag",  " ----------------- $deviceToken")
        authApi.logOut(UserLogOut(deviceToken = deviceToken!!))
        //  удалить токен при выходе
        sharedPref.edit().putString(TOKEN,"").apply()
    }

    companion object {
        const val DEVICE_ID = "DEVICE_ID"
        const val TOKEN = "TOKEN"
        const val USER_ID = "USER_ID"

    }
}