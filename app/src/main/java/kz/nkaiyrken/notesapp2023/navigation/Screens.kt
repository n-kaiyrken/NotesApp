package kz.nkaiyrken.notesapp2023.navigation

sealed class Screens(
    val route: String
) {

    object StartScreen: Screens(ROUTE_START)
    object MainScreen: Screens(ROUTE_MAIN)
    object AddScreen: Screens(ROUTE_ADD)
    object NoteScreen: Screens(ROUTE_NOTE)

    companion object {
        const val ROUTE_START = "start"
        const val ROUTE_MAIN = "main"
        const val ROUTE_ADD = "add"
        const val ROUTE_NOTE = "note"
    }
}
