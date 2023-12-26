package kz.nkaiyrken.notesapp2023.domain

import androidx.lifecycle.LiveData
import kz.nkaiyrken.notesapp2023.db.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.domain.entity.Note

interface DatabaseRepository {

    val readAll: LiveData<List<Note>>

    suspend fun create(note: NoteDBModel, onSuccess: ()-> Unit)

    suspend fun delete(note: NoteDBModel, onSuccess: ()-> Unit)

    suspend fun update(note: NoteDBModel, onSuccess: ()-> Unit)

    fun signOut() {}

    fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}

}