package nl.ng.projectcargohubapp.ui.screens.login.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.api.presentation.AuthUiEvent
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun LoginFields(viewModel: MainViewModel) {
    val state = viewModel.state

    Text(
        text = stringResource(R.string.email)
    )
    Spacer(modifier = Modifier.padding(top = 5.dp))
    TextField(
        value = state.signInEmail,
        onValueChange = { viewModel.onEvent(AuthUiEvent.SignInEmailChanged(it)) },
        label = {
            Text(
                text = stringResource(R.string.typeEmail),
                color = ColorPalette.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = ColorPalette.White)
    )

    Spacer(modifier = Modifier.padding(top = 20.dp))
    Text(text = stringResource(R.string.password))
    Spacer(modifier = Modifier.padding(top = 5.dp))
    TextField(
        value = state.signInPassword,
        onValueChange = { viewModel.onEvent(AuthUiEvent.SignInPasswordChanged(it)) },
        label = {
            Text(
                text = stringResource(R.string.typePassword),
                color = ColorPalette.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = ColorPalette.White),
        visualTransformation = PasswordVisualTransformation()
    )
    Spacer(modifier = Modifier.padding(top = 20.dp))
}
