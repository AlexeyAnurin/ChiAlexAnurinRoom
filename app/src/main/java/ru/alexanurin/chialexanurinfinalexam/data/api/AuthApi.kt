package ru.alexanurin.chialexanurinfinalexam.data.api

import retrofit2.http.*
import ru.alexanurin.chialexanurinfinalexam.data.model.*

//   interface holds the API call
interface AuthApi {

    //  запрос на создание нового пользователя.
    @POST("api-mobile/user/create")
    suspend fun createNewUser(
        @Body newUser: NewUser
    ): NewUserDTO

    //  вход в приложение.
    @POST("api-mobile/user/login")
    suspend fun loginUser(
        @Body loginUser: LoginUser
    ): LoginUserDTO

    //  запрос данных о пользователе по id.
    @GET("api-mobile/user/get-by-id")
    suspend fun getUserInfo(
        @Query("id") id: Int
    ): UserInfoDTO

    //  Выход из приложения.
    @POST("api-mobile/user/logout")
    suspend fun logOut(
        @Body userLogOut: UserLogOut
    )
}