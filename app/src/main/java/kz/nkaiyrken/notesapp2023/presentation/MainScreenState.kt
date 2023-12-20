package kz.nkaiyrken.notesapp2023.presentation

import kz.nkaiyrken.notesapp2023.domain.entity.Note

sealed class MainScreenState {

    object Initial : MainScreenState()

    object Loading : MainScreenState()

    data class Notes(
        val notes: List<Note>
    )
}
