package ru.alexanurin.chialexanurinfinalexam.data.repo

import kotlinx.coroutines.flow.Flow
import ru.alexanurin.chialexanurinfinalexam.data.database.Notes
import ru.alexanurin.chialexanurinfinalexam.data.database.NotesDao

class NoteRepository(private val notesDao: NotesDao) {

    @Suppress("RedundantSuspendModifier")
    suspend fun insertNote(notes: Notes?) {
        notesDao.insertNote(notes)
    }

    fun getAllNotes(): Flow<List<Notes>> {
        return notesDao.getAllNotes()
    }

    @Suppress("RedundantSuspendModifier")
    suspend fun deleteNote(id: Int) {
        notesDao.deleteNote(id)
    }

}