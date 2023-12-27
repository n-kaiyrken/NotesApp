package kz.nkaiyrken.notesapp2023.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import kz.nkaiyrken.notesapp2023.db.firebase.FirebaseNoteResponse
import kz.nkaiyrken.notesapp2023.db.room.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.domain.entity.Note

class Mapper {

    fun mapLiveDataNoteDBModelToNote(noteDbModelLiveData: LiveData<List<NoteDBModel>>): LiveData<List<Note>> {
        return noteDbModelLiveData.map {
            mapNoteDBModelsToNotes(it)
        }
    }

    fun mapNoteDBModelToNote(noteDBModel: NoteDBModel): Note {
        return Note(
            id = noteDBModel.id.toString(),
            title = noteDBModel.title,
            description = noteDBModel.description
        )
    }

    fun mapNoteToNoteDBModel(note: Note): NoteDBModel {
        return NoteDBModel(
            title = note.title,
            description = note.description
        )
    }

    fun mapNoteDBModelsToNotes(noteDBModels: List<NoteDBModel>): List<Note> {
        return noteDBModels.map { mapNoteDBModelToNote(it) }
    }

    fun mapFirebaseNoteResponseToNote(firebaseNoteResponse: FirebaseNoteResponse): Note {
        return Note(
            id = firebaseNoteResponse.firebase_id,
            title = firebaseNoteResponse.title,
            description = firebaseNoteResponse.description
        )
    }

    fun mapFirebaseNoteResponsesToNotes(firebaseNoteResponses: List<FirebaseNoteResponse>): List<Note> {
        return firebaseNoteResponses.map { mapFirebaseNoteResponseToNote(it) }
    }

    fun mapLiveDataFirebaseNoteResponseToNote(firebaseNoteResponseLiveData: LiveData<List<FirebaseNoteResponse>>): LiveData<List<Note>> {
        return firebaseNoteResponseLiveData.map {
            Log.d("checkData", "Notes Mapper: $it")
            mapFirebaseNoteResponsesToNotes(it)
        }
    }
}