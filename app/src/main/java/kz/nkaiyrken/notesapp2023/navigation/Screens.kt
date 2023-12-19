package kz.nkaiyrken.notesapp2023.navigation

sealed class Screens(
    val route: String
) {

    object Start: Screens(ROUTE_START)
    object Main: Screens(ROUTE_MAIN)
    object Add: Screens(ROUTE_ADD)
    object Note: Screens(ROUTE_NOTE)

    companion object {
        const val ROUTE_START = "start"
        const val ROUTE_MAIN = "main"
        const val ROUTE_ADD = "add"
        const val ROUTE_NOTE = "note"
    }
}
