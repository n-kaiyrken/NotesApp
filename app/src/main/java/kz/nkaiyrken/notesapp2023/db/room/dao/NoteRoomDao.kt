package kz.nkaiyrken.notesapp2023.db.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kz.nkaiyrken.notesapp2023.db.room.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.utils.Constants

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM ${Constants.Keys.NOTES_TABLE}")
    fun getAllNotes(): LiveData<List<NoteDBModel>>

    @Insert
    suspend fun addNote(note: NoteDBModel)

    @Update
    suspend fun updateNote(note: NoteDBModel)

    @Delete
    suspend fun deleteNote(note: NoteDBModel)
}