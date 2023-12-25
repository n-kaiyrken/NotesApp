package kz.nkaiyrken.notesapp2023.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
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
                viewModel = viewModel,
                onRoomButtonClickListener = {
                    navigationState.navigateTo(route = Screens.MainScreen.route)
                },
                onFirebaseButtonClickListener = {
                    navigationState.navigateTo(route = Screens.MainScreen.route)
                },
            )
        }
        composable(route = Screens.AddScreen.route) {
            AddScreen(
                viewModel = viewModel,
                onSaveButtonClickListener = {
                    navigationState.navigateTo(Screens.MainScreen.route)
                }
            )
        }
        composable(route = Screens.MainScreen.route) {
            MainScreen(
                viewModel = viewModel,
                onFABClickListener = { navigationState.navigateTo(Screens.NoteScreen.ROUTE_FOR_ARGS) },
                onNoteClickListener = {
                    navigationState.navigateTo(
                        Screens.NoteScreen.getRouteWithArgs(
                            it
                        )
                    )
                },
            )
        }
        composable(
            route = Screens.NoteScreen.route,
            arguments = listOf(
                navArgument(name = Screens.KEY_NOTE) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val noteJson = backStackEntry.arguments?.getString(Screens.KEY_NOTE) ?: ""
            val note = Gson().fromJson(noteJson, Note::class.java)
            NoteScreen(
                note = note,
                viewModel = viewModel,
                onBackPressed = { navigationState.navigateTo(Screens.MainScreen.route) }
            )
        }
        composable(
            route = Screens.NoteScreen.ROUTE_FOR_ARGS,
        ) {
            NoteScreen(
                note = Note(),
                viewModel = viewModel,
                onBackPressed = { navigationState.navigateTo(Screens.MainScreen.route) }
            )
        }
    }
}