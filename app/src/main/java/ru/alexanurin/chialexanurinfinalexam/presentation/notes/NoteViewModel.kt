package ru.alexanurin.chialexanurinfinalexam.presentation.notes

import android.content.SharedPreferences
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import ru.alexanurin.chialexanurinfinalexam.presentation.notes.NoteListFragment.Companion.DELETED_NOTES
import ru.alexanurin.chialexanurinfinalexam.data.database.Notes
import ru.alexanurin.chialexanurinfinalexam.data.repo.NoteRepository


@KoinApiExtension
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel(), KoinComponent {

    private val sharedPref: SharedPreferences = get() // direct

    //  getAllNotes() возвращает Flow. Приведём Flow к LiveData и присвоим свойству.
    //  collect и emit уже внутри расширения asLiveData().
    val noteAddEvent: LiveData<List<Notes>> = noteRepository.getAllNotes().asLiveData()
    var decrementEvent = MutableLiveData<Int>()

    //  Обновление счётчика удалённых заметок.
    private fun decrementCounter() {
        var decrementCounter = sharedPref.getInt(DELETED_NOTES, 0)
        decrementCounter += 1
        sharedPref.edit().putInt(DELETED_NOTES, decrementCounter).apply()
    }

    //  Удаление выбранной заметки через корутину. Когда удаление завершится, будет вызван
    // decrementCounter() и значение счётчика будет помещено в контейнер LiveData.
    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(id).also { decrementCounter() }
                .also { decrementEvent.postValue(sharedPref.getInt(DELETED_NOTES, 0)) }
        }
    }
}
