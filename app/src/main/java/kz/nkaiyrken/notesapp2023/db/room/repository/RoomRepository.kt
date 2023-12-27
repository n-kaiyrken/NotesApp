package kz.nkaiyrken.notesapp2023.db.room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kz.nkaiyrken.notesapp2023.db.room.AppRoomDatabase
import kz.nkaiyrken.notesapp2023.domain.DatabaseRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.Mapper

class RoomRepository(
    private val mapper: Mapper,
    context: Context
) : DatabaseRepository {

    val noteRoomDao = AppRoomDatabase.getInstance(context = context).getRoomDao()

    override fun readAll(): LiveData<List<Note>> =
        mapper.mapLiveDataNoteDBModelToNote(noteRoomDao.getAllNotes())

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = mapper.mapNoteToNoteDBModel(note))
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = mapper.mapNoteToNoteDBModel(note))
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = mapper.mapNoteToNoteDBModel(note))
        onSuccess()
    }
}