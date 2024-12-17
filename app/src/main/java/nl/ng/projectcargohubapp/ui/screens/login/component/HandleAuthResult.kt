package nl.ng.projectcargohubapp.ui.screens.login.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.api.domain.AuthResult
import nl.ng.projectcargohubapp.navigation.Screen

@Composable
fun HandleAuthResult(viewModel: MainViewModel, navController: NavController){
    val context = LocalContext.current
    val notAuthorized = stringResource(R.string.notAuthorized)
    val unknownError = stringResource(R.string.wrongCredentials)

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Screen.TripScreen.route)
                }
                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        notAuthorized,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        unknownError,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}