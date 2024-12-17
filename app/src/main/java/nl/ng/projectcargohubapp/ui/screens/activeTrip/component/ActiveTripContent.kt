package nl.ng.projectcargohubapp.ui.screens.activeTrip.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInProcessButtons
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel

@Composable
fun ActiveTripContent(
    navController: NavController,
    tripList: List<Items>,
    viewModel: MainViewModel,
    activeTripViewModel: ActiveTripViewModel,
    checkInViewModel: CheckInViewModel,
    applicationContext: Context
) {
    Column {
        ButtonRow(navController)
        ActiveTripsList(
            tripList = tripList,
            navController = navController,
            viewModel = viewModel,
            activeTripViewModel = activeTripViewModel
        )
        CheckInProcessButtons(navController, checkInViewModel, applicationContext, viewModel, activeTripViewModel)
    }
}