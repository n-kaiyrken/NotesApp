package kz.nkaiyrken.notesapp2023.navigation

import android.net.Uri
import com.google.gson.Gson
import kz.nkaiyrken.notesapp2023.domain.entity.Note

sealed class Screens(
    val route: String
) {

    object StartScreen: Screens(ROUTE_START)
    object MainScreen: Screens(ROUTE_MAIN)
    object AddScreen: Screens(ROUTE_ADD)
    object NoteScreen: Screens(ROUTE_NOTE) {
        const val ROUTE_FOR_ARGS = "note"

        fun getRouteWithArgs(note: Note): String {
            val noteJson = Gson().toJson(note)
            return "$ROUTE_FOR_ARGS/${noteJson.encode()}"
        }
    }

    companion object {
        const val KEY_NOTE = "key_note"

        const val ROUTE_START = "start"
        const val ROUTE_MAIN = "main"
        const val ROUTE_ADD = "add"
        const val ROUTE_NOTE = "note/{$KEY_NOTE}"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}
