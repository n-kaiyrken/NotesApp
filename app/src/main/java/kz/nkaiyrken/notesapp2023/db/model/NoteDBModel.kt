package kz.nkaiyrken.notesapp2023.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.nkaiyrken.notesapp2023.utils.Constants

@Entity(tableName = Constants.Keys.NOTES_TABLE)
data class NoteDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
)
