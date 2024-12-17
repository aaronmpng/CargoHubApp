package nl.ng.projectcargohubapp.ui.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.login.component.*

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
    HandleAuthResult(viewModel, navController)

    Column() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp), color = ColorPalette.Blue
        ) {
            Column(modifier = Modifier.padding(40.dp)) {
                LoginHeader()
                LoginFields(viewModel)
                LoginButton(viewModel, loginViewModel)
            }
        }
        Surface(
            modifier = Modifier
                .fillMaxSize(), color = ColorPalette.White
        ) {
            LoginLogo()
        }
    }
}

