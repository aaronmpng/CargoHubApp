package nl.ng.projectcargohubapp.ui.screens.closedTrip

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.ArrivedTripViewModel
import nl.ng.projectcargohubapp.ui.screens.closedTrip.component.ClosedTopAppBar
import nl.ng.projectcargohubapp.ui.screens.closedTrip.component.ClosedTripCard
import nl.ng.projectcargohubapp.ui.screens.closedTrip.component.TripButtonsRow


@Composable
fun ClosedTripScreen(
    navController: NavController,
    arrivedTripViewModel: ArrivedTripViewModel,
    viewModel: MainViewModel) {

    viewModel.getTrips()
    val tripList by viewModel.tripList.collectAsState()

    Scaffold(
        backgroundColor = Color.White,
        topBar = { ClosedTopAppBar() },
        content = {
            Box(
                modifier = Modifier.padding(it)
            ) {
                Column {
                    TripButtonsRow(navController)

                    tripList.forEach { tripItem ->
                        ClosedTripCard(tripItem, arrivedTripViewModel)
                    }
                }
            }
        }
    )
}

