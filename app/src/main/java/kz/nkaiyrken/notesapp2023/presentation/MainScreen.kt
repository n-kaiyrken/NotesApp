package kz.nkaiyrken.notesapp2023.presentation

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kz.nkaiyrken.notesapp2023.MainViewModel
import kz.nkaiyrken.notesapp2023.MainViewModelFactory
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.navigation.Screens
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navHostController.navigate(Screens.AddScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icon",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
            //contentPadding = PaddingValues(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
//            items(
//                items = notes,
//                key = { it.id }
//            ) {
//                NoteCard(
//                    note = it,
//                    navHostController = navHostController
//                )
//            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable {
                navHostController.navigate(route = Screens.NoteScreen.route)
            },
        elevation = 6.dp
    ) {
        Column {
            Text(
                text = note.title,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.description)
        }

    }
}

@Composable
@Preview(showBackground = true)
fun NoteCardPreview() {
    NotesApp2023Theme {
        NoteCard(
            note = Note(1, "Some text", "Some title"),
            navHostController = rememberNavController()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    NotesApp2023Theme {
        MainScreen(
            navHostController = rememberNavController(),
            viewModel = MainViewModel(LocalContext.current as Application)
            )
    }
}