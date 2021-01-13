package ru.alexanurin.chialexanurinfinalexam.presentation.notes

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import ru.alexanurin.chialexanurinfinalexam.presentation.notes.NoteListFragment.Companion.SAVED_NOTES
import ru.alexanurin.chialexanurinfinalexam.data.database.Notes
import ru.alexanurin.chialexanurinfinalexam.data.repo.NoteRepository

@KoinApiExtension
class AddNoteViewModel(private val noteRepository: NoteRepository,  private val sharedPref: SharedPreferences) : ViewModel() {

    //  Инкремент счётчика добавленных заметок, хранится в SharedPreferences.
    private fun incrementCounter() {
        var savedCounter = sharedPref.getInt(SAVED_NOTES, 0)
        savedCounter += 1
        sharedPref.edit().putInt(SAVED_NOTES, savedCounter).apply()
    }

    //  Вставка в БД новой заметки через корутину. Когда вставка произойдёт, то выполнится incrementCounter().
    fun insertNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote(note).also { incrementCounter() }
        }

    }
}