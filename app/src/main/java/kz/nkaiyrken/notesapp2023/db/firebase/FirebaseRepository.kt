package kz.nkaiyrken.notesapp2023.db.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kz.nkaiyrken.notesapp2023.domain.DatabaseRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.Constants
import kz.nkaiyrken.notesapp2023.utils.FIREBASE_ID
import kz.nkaiyrken.notesapp2023.utils.Mapper

class FirebaseRepository(
    private val mapper: Mapper
) : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override fun readAll(): LiveData<List<Note>> =
        mapper.mapLiveDataFirebaseNoteResponseToNote(AllNotesLiveData())

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        val noteId = database.push().key.toString()
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.DESCRIPTION] = note.description

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to add new note") }
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        database.child(note.id)
            .removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to delete note") }
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        val noteId = note.id
        val mapNotes = hashMapOf<String, Any>()

        mapNotes[FIREBASE_ID] = noteId
        mapNotes[Constants.Keys.TITLE] = note.title
        mapNotes[Constants.Keys.DESCRIPTION] = note.description

        database.child(noteId)
            .updateChildren(mapNotes)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { Log.d("checkData", "Failed to update note") }
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToFirebase(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

}