package ru.alexanurin.chialexanurinroom.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME = "Notes"

//  @Entity отмечает объект для хранения в базе в виде таблицы.
@Entity(tableName = TABLE_NAME)
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var note: String?,
    )

