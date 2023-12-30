package kz.nkaiyrken.notesapp2023.presentation

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.nkaiyrken.notesapp2023.MainViewModel
import kz.nkaiyrken.notesapp2023.R
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

@Composable
fun NoteScreen(
    note: Note,
    viewModel: MainViewModel,
    openMainScreen: () -> Unit,
) {

    var title by remember { mutableStateOf(note.title) }
    var description by rememberSaveable { mutableStateOf(note.description) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.onBackPressed(
                            note = note,
                            title = title,
                            description = description
                        )
                        openMainScreen()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            modifier = Modifier.padding(start = 10.dp),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = MaterialTheme.colors.primary,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 10.dp,
                    bottom = 10.dp,
                    start = 10.dp,
                    end = 10.dp
                )
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.title)) },
                singleLine = true
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .padding(it)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
    BackHandler {
        viewModel.onBackPressed(note = note, title = title, description = description)
        openMainScreen()
    }
}

@Composable
@Preview(showBackground = true)
fun NoteScreenPreview() {
    NotesApp2023Theme {
        NoteScreen(
            note = Note(
                "Note 1",
                "Text text text text text text text text text text text text text"
            ),
            MainViewModel(LocalContext.current as Application),
            openMainScreen = {}
        )
    }
}