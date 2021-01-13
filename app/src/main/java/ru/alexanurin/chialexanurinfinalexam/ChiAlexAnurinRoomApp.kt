package ru.alexanurin.chialexanurinfinalexam

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//  Запускаем Koin.
class ChiAlexAnurinRoomApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChiAlexAnurinRoomApp)
            modules(
                listOf(commonModule)
            )
        }
    }
}