package kz.nkaiyrken.notesapp2023.utils

import kz.nkaiyrken.notesapp2023.db.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.domain.entity.Note

class Mapper {

    fun mapNoteDBModelToNote(noteDBModel: NoteDBModel): Note {
        return Note(
            id = noteDBModel.id,
            title = noteDBModel.title,
            description = noteDBModel.description
        )
    }

    fun mapNoteToNoteDBModel(note: Note): NoteDBModel {
        return NoteDBModel(
            id = note.id,
            title = if (note.title.isEmpty()) "Запись {${DateUtils.getFormattedDate()}}" else note.title,
            description = note.description
        )
    }
}