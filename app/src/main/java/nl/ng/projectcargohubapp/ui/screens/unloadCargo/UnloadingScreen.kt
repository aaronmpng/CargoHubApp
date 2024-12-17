package nl.ng.projectcargohubapp.ui.screens.unloadCargo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel
import nl.ng.projectcargohubapp.ui.screens.unloadCargo.component.UnloadingContent

@Composable
fun UnloadingScreen(
    navController: NavController,
    checkOutViewModel: CheckOutViewModel,
    viewModel: MainViewModel = hiltViewModel(),
    unloadCargoViewModel: UnloadCargoViewModel = hiltViewModel()
) {
    Scaffold(
        backgroundColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.unloadOrderTrip),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                backgroundColor = ColorPalette.Blue
            )
        },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                UnloadingContent(navController, viewModel, unloadCargoViewModel, checkOutViewModel)
            }
        }
    )
}

