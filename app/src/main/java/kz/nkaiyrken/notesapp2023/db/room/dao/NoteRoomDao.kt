package kz.nkaiyrken.notesapp2023.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kz.nkaiyrken.notesapp2023.db.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.domain.entity.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun addNote(note: NoteDBModel)

    @Update
    suspend fun updateNote(note: NoteDBModel)

    @Delete
    suspend fun deleteNote(note: NoteDBModel)
}