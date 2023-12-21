package kz.nkaiyrken.notesapp2023

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.nkaiyrken.notesapp2023.db.room.AppRoomDatabase
import kz.nkaiyrken.notesapp2023.db.room.dao.NoteRoomDao
import kz.nkaiyrken.notesapp2023.db.room.repository.RoomRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.Mapper
import kz.nkaiyrken.notesapp2023.utils.REPOSITORY
import kz.nkaiyrken.notesapp2023.utils.TYPE_FIREBASE
import kz.nkaiyrken.notesapp2023.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application
    val mapper = Mapper()

    private var _notes = MutableLiveData<List<Note>>(listOf())
    val notes: LiveData<List<Note>> = _notes

    fun getAllNotes(): LiveData<List<Note>> {
        _notes.value = REPOSITORY.readAll.value
        return REPOSITORY.readAll
    }

    fun initData(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNoteToRoom(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(mapper.mapNoteToNoteDBModel(note)) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
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