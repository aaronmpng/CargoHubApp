package nl.ng.projectcargohubapp.ui.screens.unloadCargo.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel
import nl.ng.projectcargohubapp.ui.screens.unloadCargo.UnloadCargoViewModel

@Composable
fun UnloadingContent(
    navController: NavController,
    viewModel: MainViewModel,
    unloadCargoViewModel: UnloadCargoViewModel,
    checkOutViewModel: CheckOutViewModel
) {

    viewModel.getTrips()
    val tripList by viewModel.tripList.collectAsState()

    viewModel.getOrders()
    val orderState by viewModel.orderList.collectAsState()

    Column {
        tripList.forEachIndexed { tripIndex, tripItem ->
            unloadCargoViewModel.checkTripStatusActive(tripList, tripIndex + 1)
            if (tripItem.status == "ACTIVE") {
                UnloadingTripItem(
                    navController,
                    orderState,
                    tripList,
                    viewModel,
                    tripIndex + 1,
                    unloadCargoViewModel,
                    checkOutViewModel
                )
            }
        }
    }
}