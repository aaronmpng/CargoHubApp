package nl.ng.projectcargohubapp.ui.screens.activeTrip.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel

@Composable
fun ActiveTripsList(
    tripList: List<Items>,
    navController: NavController,
    viewModel: MainViewModel,
    activeTripViewModel: ActiveTripViewModel
) {
    Column() {
        tripList.forEachIndexed { index, item ->
            if (activeTripViewModel.checkIfStatusActive(tripList, index + 1)) {
                ActiveTripCard(
                    item = item,
                    navController = navController,
                    viewModel = viewModel,
                    activeTripViewModel = activeTripViewModel
                )
            }
        }
    }
}

