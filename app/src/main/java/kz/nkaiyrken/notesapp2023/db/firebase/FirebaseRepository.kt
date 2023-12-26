package kz.nkaiyrken.notesapp2023.db.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database
import kz.nkaiyrken.notesapp2023.db.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.domain.DatabaseRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.Constants
import kz.nkaiyrken.notesapp2023.utils.EMAIL
import kz.nkaiyrken.notesapp2023.utils.FIREBASE_ID
import kz.nkaiyrken.notesapp2023.utils.PASSWORD

class FirebaseRepository: DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    override val readAll: LiveData<List<Note>> = AllNotesLiveData()

    override suspend fun create(note: NoteDBModel, onSuccess: () -> Unit) {
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

    override suspend fun delete(note: NoteDBModel, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun update(note: NoteDBModel, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        mAuth.signOut()
    }

    override fun connectToFirebase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD )
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFail(it.message.toString()) }
            }
    }

}