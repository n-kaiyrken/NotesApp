package kz.nkaiyrken.notesapp2023.presentation

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
fun AddScreen(
    viewModel: MainViewModel,
    onSaveButtonClickListener: () -> Unit,
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.add_new_note),
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
                isButtonEnabled = title.isNotEmpty() && description.isNotEmpty()
            },
            label = { Text(stringResource(R.string.title)) },
            isError = title.isEmpty()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
                isButtonEnabled = title.isNotEmpty() && description.isNotEmpty()
            },
            label = { Text(stringResource(R.string.description)) },
            isError = description.isEmpty()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                viewModel.addNoteToRoom(Note(title = title, description = description)) {
                    onSaveButtonClickListener()
                }
            },
            enabled = isButtonEnabled
        ) {
            Text(
                text = stringResource(R.string.save),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddScreenPreview() {
    NotesApp2023Theme {
        AddScreen(
            MainViewModel(LocalContext.current as Application),
            onSaveButtonClickListener = {}
        )
    }
}
