package kz.nkaiyrken.notesapp2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.nkaiyrken.notesapp2023.navigation.AppNavGraph
import kz.nkaiyrken.notesapp2023.navigation.rememberNavigationState
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesApp2023Theme {

                val viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(application))
                val navigationState = rememberNavigationState()

                Scaffold {paddingValues ->
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        AppNavGraph(paddingValues, viewModel, navigationState)
                    }
                }
            }
        }
    }
}