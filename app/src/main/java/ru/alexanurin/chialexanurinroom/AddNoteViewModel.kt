package ru.alexanurin.chialexanurinroom

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.alexanurin.chialexanurinroom.NoteListFragment.Companion.SAVED_NOTES
import ru.alexanurin.chialexanurinroom.database.Notes

@KoinApiExtension
class AddNoteViewModel(private val noteRepository: NoteRepository) : ViewModel(), KoinComponent {

    // экземпляр SharedPreferences, созданный в modules через Koin.
    private val sharedPref: SharedPreferences = get()

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