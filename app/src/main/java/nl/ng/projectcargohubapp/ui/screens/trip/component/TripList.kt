package nl.ng.projectcargohubapp.ui.screens.trip.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import nl.ng.projectcargohubapp.ui.screens.trip.TripViewModel

@Composable
 fun TripList(
    state: List<Items>,
    orderItems: List<OrderItems>,
    navController: NavController,
    viewModel: MainViewModel,
    tripViewModel: TripViewModel,
    checkInViewModel: CheckInViewModel
) {
    state.forEachIndexed { _, stateItem ->
        TripCard(stateItem, orderItems, navController, viewModel, tripViewModel, checkInViewModel)
    }
}