package kz.nkaiyrken.notesapp2023

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.utils.TYPE_FIREBASE
import kz.nkaiyrken.notesapp2023.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        Log.d("test viewmodel", "MainViewModel created")
    }

    private val dbTypeLivedata = MutableLiveData<String>()

    private val _notesLivedata = MutableLiveData<List<Note>>()
    val notesLivedata: LiveData<List<Note>> = _notesLivedata

    private val notes = mutableListOf<Note>().apply {
        repeat(10) {
            this.add(
                Note(
                    id = it,
                    description = "Some text $it",
                    title = "Title $it"
                )
            )
        }
    }

    fun initData(type: String) {
        dbTypeLivedata.value = type
        _notesLivedata.value = when (type) {
            TYPE_ROOM -> {
                notes
            }
            TYPE_FIREBASE -> {
                listOf()
            }
            else -> {
                listOf()
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