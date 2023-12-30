package kz.nkaiyrken.notesapp2023.utils

import kz.nkaiyrken.notesapp2023.domain.DatabaseRepository

const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"
const val FIREBASE_ID = "firebase_id"
const val EMPTY = ""

lateinit var REPOSITORY: DatabaseRepository

object Constants {

    object Keys {
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
    }

}