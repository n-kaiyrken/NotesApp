package kz.nkaiyrken.notesapp2023

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.nkaiyrken.notesapp2023.db.firebase.FirebaseRepository
import kz.nkaiyrken.notesapp2023.db.room.repository.RoomRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.Mapper
import kz.nkaiyrken.notesapp2023.utils.REPOSITORY
import kz.nkaiyrken.notesapp2023.utils.TYPE_FIREBASE
import kz.nkaiyrken.notesapp2023.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private val mapper = Mapper()

    fun getAllNotes(): LiveData<List<Note>> {
        Log.d("checkData", "MainViewModel ${REPOSITORY.readAll().value}")
        return REPOSITORY.readAll()
    }

    fun initData(
        type: String,
        email: String = "",
        password: String = "",
        onSuccess: () -> Unit
    ) {
        when (type) {
            TYPE_ROOM -> {
                REPOSITORY = RoomRepository(mapper = mapper, context = context)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = FirebaseRepository(mapper = mapper)
                REPOSITORY.connectToFirebase(
                    email = email,
                    password = password,
                    onSuccess = onSuccess,
                    onFail = { Log.d("checkData", "Error: $it") }
                )
            }
        }
    }

    fun addNoteToRoom(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note = note) {

            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {

            }
        }
    }

    fun onBackPressed(note: Note, title: String, description: String) {
        if (note.id == "") {
            if (title.trim().isNotEmpty() || description.trim().isNotEmpty())
                addNoteToRoom(Note(title = title, description = description)) {

                }
        } else {
            if (title.trim().isNotEmpty() || description.trim().isNotEmpty())
                updateNote(Note(id = note.id, title = title, description = description))
            else deleteNote(note = note)
        }
    }


}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}