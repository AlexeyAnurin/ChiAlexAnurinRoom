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
        //  применение полученного device token при создании нового пользователя.
        val deviceToken = getDeviceToken()
        Log.d("tag", "deviceToken $deviceToken")
        //  через функцию-расширение добавление device token к запросу создания нового пользователя.
        return authApi.createNewUser(newUser.toDto(deviceToken))
    }

    //  Получение device token через либу fingerprinter.
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
        //  через функцию-расширение добавление device token к запросу входа в приложение.
        val response = authApi.loginUser(loginUserModel.toDto(deviceToken))
        //  Из ответа от сервера взять токен для подписи запросов/повторного входа без авторизации.
        sharedPref.edit().putString(TOKEN, response.accessToken).apply()
        //  Из ответа от сервера взять Id пользователя для доступа к его профилю.
        sharedPref.edit().putInt(USER_ID, response.loggedUserData.id).apply()
        println("sharedPref: ${sharedPref.getString(TOKEN, "")}") //L
        return response
    }

    //  запрос данных о профиле пользователя через его Id, полученный при регистрации.
    suspend fun getUserInfo(): UserInfoDTO {
        return authApi.getUserInfo(sharedPref.getInt(USER_ID, 0))
    }

    //  выход из приложения.
    suspend fun logOut() {
        //  получение device Token для запроса на выход из приложения.
        val deviceToken = sharedPref.getString(DEVICE_ID, "")
        authApi.logOut(UserLogOut(deviceToken = deviceToken!!))
        //  удалить токен при выходе
        sharedPref.edit().putString(TOKEN, "").apply()
    }

    companion object {
        const val DEVICE_ID = "DEVICE_ID"
        const val TOKEN = "TOKEN"
        const val USER_ID = "USER_ID"
    }
}