package kz.nkaiyrken.notesapp2023.presentation

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kz.nkaiyrken.notesapp2023.MainViewModel
import kz.nkaiyrken.notesapp2023.R
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.navigation.Screens
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

@Composable
fun NoteScreen(
    note: Note,
    viewModel: MainViewModel,
    onBackPressed: () -> Unit,
) {

    var title by remember { mutableStateOf(note.title) }
    var description by rememberSaveable { mutableStateOf(note.description) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 50.dp,
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
//        Text(
//            text = note.title,
//            modifier = Modifier.fillMaxWidth(),
//            color = Color.Black,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            fontSize = 30.sp
//        )
        TextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.title)) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxSize(),
        )
    }
    BackHandler {
        if (note.id == 0) {
            if (title.isNotEmpty() || description.isNotEmpty())
                viewModel.addNoteToRoom(Note(title = title, description = description)) {

                }
        } else {
            if (title.isNotEmpty() || description.isNotEmpty())
                viewModel.updateNote(Note(id = note.id, title = title, description = description))
            else viewModel.deleteNote(note = note)
        }
        onBackPressed()
    }
}

@Composable
@Preview(showBackground = true)
fun NoteScreenPreview() {
    NotesApp2023Theme {
        NoteScreen(
            note = Note(
                1,
                "Note 1",
                "Text text text text text text text text text text text text text"
            ),
            MainViewModel(LocalContext.current as Application),
            onBackPressed = {}
        )
    }
}