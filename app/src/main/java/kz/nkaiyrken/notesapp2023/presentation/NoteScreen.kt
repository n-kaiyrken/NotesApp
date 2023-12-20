package kz.nkaiyrken.notesapp2023.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kz.nkaiyrken.notesapp2023.domain.entity.Note
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme

@Composable
fun NoteScreen(
    note: Note,
    navHostController: NavHostController
) {
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
        Text(
            text = note.title,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = note.text,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onSecondary,
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NoteScreenPreview() {
    NotesApp2023Theme {
        NoteScreen(
            note = Note(1, "Text text text text text text text text text text text text text", "Note 1"),
            navHostController = rememberNavController()
        )
    }
}