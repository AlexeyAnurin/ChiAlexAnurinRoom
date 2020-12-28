package ru.alexanurin.chialexanurinroom

import kotlinx.coroutines.flow.Flow
import ru.alexanurin.chialexanurinroom.database.Notes
import ru.alexanurin.chialexanurinroom.database.NotesDao

class NoteRepository(private val notesDao: NotesDao) {

    fun insertNote(notes: Notes?) {
        notesDao.insertNote(notes)
    }

    fun getAllNotes(): Flow<List<Notes>> {
       return notesDao.getAllNotes()
    }

    fun deleteNote(id: Int){
        notesDao.deleteNote(id)
    }

}