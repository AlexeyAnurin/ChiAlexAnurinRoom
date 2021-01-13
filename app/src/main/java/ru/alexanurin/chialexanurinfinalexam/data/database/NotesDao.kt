package ru.alexanurin.chialexanurinfinalexam.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notes: Notes?)

    //  Получаем данные из таблицы через Flow.
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("DELETE FROM $TABLE_NAME WHERE id = :id")
    fun deleteNote(id: Int)
}