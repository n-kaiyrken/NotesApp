package kz.nkaiyrken.notesapp2023.utils

import kz.nkaiyrken.notesapp2023.db.room.repository.RoomRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: RoomRepository

object Constants {

    object Keys {
        const val NOTES_DATABASE = "notes_database"
        const val NOTES_TABLE = "notes_table"
    }

}