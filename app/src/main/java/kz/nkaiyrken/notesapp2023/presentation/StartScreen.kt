package kz.nkaiyrken.notesapp2023.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kz.nkaiyrken.notesapp2023.navigation.Screens
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

@Composable
fun StartScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "What will we use?")
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp),
                onClick = {
                    navHostController.navigate(route = Screens.MainScreen.route)
                }
            ) {
                Text(text = "Room database")
            }
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp),
                onClick = {
                    navHostController.navigate(route = Screens.MainScreen.route)
                }
            ) {
                Text(text = "Firebase database")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    NotesApp2023Theme {
        StartScreen(navHostController = rememberNavController())
    }
}