package ru.alexanurin.chialexanurinroom

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexanurin.chialexanurinroom.database.AppDatabase


val commonModule = module {

    //  Один экземпляр БД.
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app-database").build()
            .getNotesDao()
    }
    //  Репозиторий.
    single { NoteRepository(get()) }
    //  ViewModels.
    viewModel { NoteViewModel(get()) }
    viewModel { AddNoteViewModel(get()) }

    //  один экземпляр SharedPref с настройками.
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            "pref",
            Context.MODE_PRIVATE
        )
    }

}


