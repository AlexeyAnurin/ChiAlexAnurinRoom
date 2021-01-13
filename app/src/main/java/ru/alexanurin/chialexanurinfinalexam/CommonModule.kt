package ru.alexanurin.chialexanurinfinalexam

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fingerprintjs.android.fingerprint.Configuration
import com.fingerprintjs.android.fingerprint.FingerprinterFactory
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.alexanurin.chialexanurinfinalexam.presentation.channel.ChannelDetailsViewModel
import ru.alexanurin.chialexanurinfinalexam.presentation.channel.ChannelViewModel
import ru.alexanurin.chialexanurinfinalexam.data.database.AppDatabase
import ru.alexanurin.chialexanurinfinalexam.presentation.login.CreateNewUserViewModel
import ru.alexanurin.chialexanurinfinalexam.presentation.login.LoginViewModel
import ru.alexanurin.chialexanurinfinalexam.data.api.AuthApi
import ru.alexanurin.chialexanurinfinalexam.data.api.ChannelApi
import ru.alexanurin.chialexanurinfinalexam.data.network.RetrofitBuilder
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.CurlLoggingInterceptor
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.HeadersInterceptor
import ru.alexanurin.chialexanurinfinalexam.data.network.interceptor.HttpLogger
import ru.alexanurin.chialexanurinfinalexam.data.repo.ChannelsRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.notes.AddNoteViewModel
import ru.alexanurin.chialexanurinfinalexam.presentation.notes.NoteViewModel
import ru.alexanurin.chialexanurinfinalexam.data.repo.AuthRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.splash.SplashViewModel
import ru.alexanurin.chialexanurinfinalexam.data.repo.NoteRepository
import ru.alexanurin.chialexanurinfinalexam.presentation.userprofile.UserProfileViewModel

val commonModule = module {

        //
        factory { CurlLoggingInterceptor(get()) }

        //  для токена
        factory { HeadersInterceptor(get()) } bind Interceptor::class

        single<HttpLoggingInterceptor.Logger> { HttpLogger() }

        // get header for token
        single { RetrofitBuilder(get(), get()).retrofit() }
        single { get<Retrofit>().create(AuthApi::class.java) }
        single { get<Retrofit>().create(ChannelApi::class.java) }

        //  Один экземпляр БД.
        single {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database").build()
        }
        single { get<AppDatabase>().getNotesDao() }
        single { get<AppDatabase>().getChannelsDao() }



        //  Репозитории
        single { NoteRepository(get()) }
        single { ChannelsRepository(get(), get()) }
        single { AuthRepository(get(), get(), get()) }

        //  один экземпляр SharedPref с настройками.
        single<SharedPreferences> {
            androidContext().getSharedPreferences(
                "pref",
                Context.MODE_PRIVATE
            )
        }
        single {
            FingerprinterFactory
                .getInstance(androidApplication(), Configuration(version = 1))
        }
        //  ViewModels note
        viewModel { NoteViewModel(get()) }
        viewModel { AddNoteViewModel(get(), get()) }
        //  ViewModel channel
        viewModel { ChannelViewModel(get()) }
        //  ViewModels login
        viewModel { LoginViewModel(get()) }
        viewModel { CreateNewUserViewModel(get()) }
        viewModel { (channelDetailsId: Int) -> ChannelDetailsViewModel(channelDetailsId, get()) }
        //  ViewModel splash
        viewModel { SplashViewModel(get()) }
        //  ViewModel UserInfo
        viewModel { UserProfileViewModel(get()) }
    }


