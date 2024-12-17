package nl.ng.projectcargohubapp.ui.screens.arrivedTrip

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.component.ArrivedTopAppBar
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.component.ArrivedTripCard
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.component.TripButtonsRow
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutProcessButtons
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel

@Composable
fun ArrivedTripScreen(
    navController: NavController,
    checkOutViewModel: CheckOutViewModel,
    arrivedTripViewModel: ArrivedTripViewModel,
    viewModel: MainViewModel = hiltViewModel(),
) {
    viewModel.getTrips()
    val tripList by viewModel.tripList.collectAsState()

    Scaffold(
        backgroundColor = ColorPalette.White,
        topBar = { ArrivedTopAppBar() },
        content = {

            Box(
                modifier = Modifier.padding(it)
            ) {
                Column {
                    TripButtonsRow()

                    tripList.forEachIndexed { index, tripItem ->
                        if (arrivedTripViewModel.checkIfStatusActive(tripList, index + 1)) {
                            ArrivedTripCard(tripItem, arrivedTripViewModel)
                        }
                    }

                    if (checkOutViewModel.finalizeTrip) {
                        arrivedTripViewModel.updateOrderStatus(
                            viewModel,
                            checkOutViewModel.orderState,
                            checkOutViewModel.i
                        )
                        arrivedTripViewModel.updateTripStatus(
                            viewModel,
                            checkOutViewModel.tripState,
                            checkOutViewModel.i
                        )
                    }
                    CheckOutProcessButtons(navController, checkOutViewModel)
                }
            }
        }
    )
}
