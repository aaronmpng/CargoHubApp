package nl.ng.projectcargohubapp.ui.screens.login.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.login.LoginViewModel

@Composable
fun LoginButton(viewModel: MainViewModel, loginViewModel: LoginViewModel) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ColorPalette.DarkBlue
        ), onClick = { loginViewModel.loginUser(viewModel) }) {
        Text(
            text = stringResource(R.string.login),
            color = ColorPalette.White
        )
    }
    Spacer(modifier = Modifier.padding(top = 20.dp))
}