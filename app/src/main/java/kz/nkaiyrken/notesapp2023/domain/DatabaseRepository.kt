package kz.nkaiyrken.notesapp2023.domain

import androidx.lifecycle.LiveData
import kz.nkaiyrken.notesapp2023.domain.entity.Note

interface DatabaseRepository {

    fun readAll(): LiveData<List<Note>>

    suspend fun create(note: Note, onSuccess: ()-> Unit)

    suspend fun delete(note: Note, onSuccess: ()-> Unit)

    suspend fun update(note: Note, onSuccess: ()-> Unit)

    fun signOut() {}

    fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {}

}