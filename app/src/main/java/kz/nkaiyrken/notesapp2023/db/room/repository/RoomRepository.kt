package kz.nkaiyrken.notesapp2023.db.room.repository

import androidx.lifecycle.LiveData
import kz.nkaiyrken.notesapp2023.db.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.db.room.dao.NoteRoomDao
import kz.nkaiyrken.notesapp2023.domain.DatabaseRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: NoteDBModel, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
    }

    override suspend fun delete(note: NoteDBModel, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
    }

    override suspend fun update(note: NoteDBModel, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
    }
}