package kz.nkaiyrken.notesapp2023.presentation

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kz.nkaiyrken.notesapp2023.MainViewModel
import kz.nkaiyrken.notesapp2023.R
import kz.nkaiyrken.notesapp2023.ui.theme.NotesApp2023Theme
import kz.nkaiyrken.notesapp2023.utils.EMPTY
import kz.nkaiyrken.notesapp2023.utils.TYPE_FIREBASE
import kz.nkaiyrken.notesapp2023.utils.TYPE_ROOM

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StartScreen(
    viewModel: MainViewModel,
    openMainScreen: () -> Unit,
) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            SignInBottomSheet(
                viewModel = viewModel,
                openMainScreen = openMainScreen,
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(R.string.what_will_we_use))
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp),
                    onClick = {
                        viewModel.initData(TYPE_ROOM) {
                            openMainScreen()
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.room_database))
                }
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp),
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.firebase_database))
                }
            }
        }
    }
}

@Composable
fun SignInBottomSheet(
    viewModel: MainViewModel,
    openMainScreen: () -> Unit
) {

    var email by remember { mutableStateOf(EMPTY) }
    var password by remember { mutableStateOf(EMPTY) }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.log_in_to_firebase),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it.trim() },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                label = { Text(text = stringResource(R.string.email)) },
                isError = email.isEmpty()
            )
            Column {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it.trim()},
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    label = { Text(text = stringResource(R.string.password)) },
                    isError = password.isEmpty() || password.length < 6
                )
                Text(
                    text = stringResource(R.string.at_least_6_characters),
                    color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    viewModel.initData(TYPE_FIREBASE, email = email, password = password) {
                        openMainScreen()
                    }
                },
                enabled = password.isNotEmpty() && email.isNotEmpty()
            ) {
                Text(text = stringResource(R.string.sign_in))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    NotesApp2023Theme {
        StartScreen(
            viewModel = MainViewModel(LocalContext.current as Application),
            openMainScreen = {}
        )
    }
}