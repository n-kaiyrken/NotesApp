package kz.nkaiyrken.notesapp2023

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kz.nkaiyrken.notesapp2023.db.room.AppRoomDatabase
import kz.nkaiyrken.notesapp2023.db.room.dao.NoteRoomDao
import kz.nkaiyrken.notesapp2023.db.room.repository.RoomRepository
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.REPOSITORY
import kz.nkaiyrken.notesapp2023.utils.TYPE_FIREBASE
import kz.nkaiyrken.notesapp2023.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initData(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
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