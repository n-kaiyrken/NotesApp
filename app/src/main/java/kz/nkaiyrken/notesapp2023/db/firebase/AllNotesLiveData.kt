package kz.nkaiyrken.notesapp2023.db.firebase

import android.renderscript.Sampler.Value
import androidx.lifecycle.LiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kz.nkaiyrken.notesapp2023.domain.entity.Note

class AllNotesLiveData: LiveData<List<Note>>() {
    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database.reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val notes = mutableListOf<Note>()
            snapshot.children.map {
                notes.add(it.getValue(Note::class.java) ?: Note())
            }
            value = notes
        }

        override fun onCancelled(error: DatabaseError) {}

    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}