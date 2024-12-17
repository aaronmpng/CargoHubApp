package nl.ng.projectcargohubapp.ui.screens.loadCargo

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripScreen
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import nl.ng.projectcargohubapp.ui.screens.loadCargo.component.LoadingScreenContent
import nl.ng.projectcargohubapp.ui.screens.loadCargo.component.LoadingScreenTopBar

@Composable
fun LoadingScreen(
    navController: NavController,
    checkInViewModel: CheckInViewModel,
    applicationContext: Context,
    activeTripViewModel: ActiveTripViewModel,
    viewModel: MainViewModel,
    loadCargoViewModel: LoadCargoViewModel = hiltViewModel()
) {
    viewModel.getTrips()
    val tripList by viewModel.tripList.collectAsState()

    viewModel.getOrders()
    val orderItems by viewModel.orderList.collectAsState()

    var toCheckInScreen by remember { mutableStateOf(false) }

    if (!toCheckInScreen) {
        Scaffold(
            backgroundColor = ColorPalette.White,
            topBar = { LoadingScreenTopBar() },
            content = {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    LoadingScreenContent(
                        tripList = tripList,
                        orderItems = orderItems,
                        selected = loadCargoViewModel.selected,
                        viewModel = viewModel,
                        onConfirmOrderClick = { loadCargoViewModel.selected = !loadCargoViewModel.selected },
                        onLoadingFinishClick = {
                            loadCargoViewModel.updateOrderStatus(viewModel, orderItems)
                            toCheckInScreen = true
                        },
                        loadCargoViewModel = loadCargoViewModel,
                        navController = navController
                    )
                }
            }
        )
    }

    if (toCheckInScreen) {
        checkInViewModel.saveButton3Value(true)
        ActiveTripScreen(
            navController,
            checkInViewModel,
            applicationContext,
            activeTripViewModel,
            viewModel
        )
    }
}
