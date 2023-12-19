package kz.nkaiyrken.notesapp2023.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kz.nkaiyrken.notesapp2023.presentation.AddScreen
import kz.nkaiyrken.notesapp2023.presentation.MainScreen
import kz.nkaiyrken.notesapp2023.presentation.NoteScreen
import kz.nkaiyrken.notesapp2023.presentation.StartScreen

@Composable
fun AppNavGraph(
    paddingValues: PaddingValues
) {
    val navigationState = rememberNavigationState()
    
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Screens.ROUTE_START
    ) {
        composable(route = Screens.Start.route) {
            StartScreen(navHostController = navigationState.navHostController)
        }
        composable(route = Screens.Add.route) {
            AddScreen(navHostController = navigationState.navHostController)
        }
        composable(route = Screens.Main.route) {
            MainScreen(navHostController = navigationState.navHostController)
        }
        composable(route = Screens.Note.route) {
            NoteScreen(navHostController = navigationState.navHostController)
        }
    }
}