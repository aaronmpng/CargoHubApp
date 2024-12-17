package nl.ng.projectcargohubapp.ui.screens.activeTrip

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.activeTrip.component.ActiveTripContent
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel

@Composable
fun ActiveTripScreen(
    navController: NavController,
    checkInViewModel: CheckInViewModel,
    applicationContext: Context,
    activeTripViewModel: ActiveTripViewModel,
    viewModel: MainViewModel
) {
    viewModel.getTrips()
    val tripList by viewModel.tripList.collectAsState()

    Scaffold(
        backgroundColor = ColorPalette.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.checkInProcess),
                        color = ColorPalette.White,
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
                ActiveTripContent(
                    navController = navController,
                    tripList = tripList,
                    viewModel = viewModel,
                    activeTripViewModel = activeTripViewModel,
                    checkInViewModel = checkInViewModel,
                    applicationContext = applicationContext
                )
            }
        }
    )
}
