package kz.nkaiyrken.notesapp2023.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kz.nkaiyrken.notesapp2023.MainViewModel
import kz.nkaiyrken.notesapp2023.MainViewModelFactory
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.presentation.AddScreen
import kz.nkaiyrken.notesapp2023.presentation.MainScreen
import kz.nkaiyrken.notesapp2023.presentation.NoteScreen
import kz.nkaiyrken.notesapp2023.presentation.StartScreen

@Composable
fun AppNavGraph(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val navigationState = rememberNavigationState()
    
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Screens.ROUTE_START
    ) {
        composable(route = Screens.StartScreen.route) {
            StartScreen(
                navHostController = navigationState.navHostController,
                viewModel = viewModel
                )
        }
        composable(route = Screens.AddScreen.route) {
            AddScreen(navHostController = navigationState.navHostController)
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen(
                navHostController = navigationState.navHostController,
                viewModel = viewModel
            )
        }
        composable(route = Screens.NoteScreen.route) {
            NoteScreen(
                navHostController = navigationState.navHostController,
                note = Note(1, "Text text text text text text", "Note 1")
            )
        }
    }
}