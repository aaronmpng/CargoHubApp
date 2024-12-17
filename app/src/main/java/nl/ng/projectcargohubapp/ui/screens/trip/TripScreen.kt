package nl.ng.projectcargohubapp.ui.screens.trip

import androidx.compose.foundation.layout.*
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
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.ArrivedTripViewModel
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import nl.ng.projectcargohubapp.ui.screens.trip.component.ButtonRow
import nl.ng.projectcargohubapp.ui.screens.trip.component.TripList

@Composable
fun TripScreen(
    navController: NavController,
    checkInViewModel: CheckInViewModel,
    activeTripViewModel: ActiveTripViewModel,
    arrivedTripViewModel: ArrivedTripViewModel,
    viewModel: MainViewModel,
    tripViewModel: TripViewModel
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getTrips()
        viewModel.getOrders()
    }

    val state by viewModel.listState.collectAsState()
    val orderItems by viewModel.orderListState.collectAsState()
    activeTripViewModel.saveOrderItems(orderItems)
    arrivedTripViewModel.saveOrderItems(orderItems)

    Scaffold(
        backgroundColor = ColorPalette.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tripToday),
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
                Column {
                    ButtonRow(navController)
                    TripList(
                        state,
                        orderItems,
                        navController,
                        viewModel,
                        tripViewModel,
                        checkInViewModel
                    )
                }
            }
        }
    )
}