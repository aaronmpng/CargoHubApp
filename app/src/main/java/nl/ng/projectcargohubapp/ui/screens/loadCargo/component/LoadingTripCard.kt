package nl.ng.projectcargohubapp.ui.screens.loadCargo.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.ui.screens.loadCargo.LoadCargoViewModel

@Composable
fun LoadingTripCard(
    orderItems: List<OrderItems>,
    tripItems: Items,
    viewModel: MainViewModel,
    onConfirmOrderClick: () -> Unit,
    loadCargoViewModel: LoadCargoViewModel,
    navController: NavController
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    orderItems.forEachIndexed { o, orderItem ->
        if (loadCargoViewModel.checkOrderIdCorrect(tripItems, orderItems, o)) {
            var nr = 0

            viewModel.getAirwayBills(orderItem.orderID)
            val airwayBillsState by viewModel.airwayBillsList.collectAsState()

            LoadingCard(
                expandedState = expandedState,
                rotationState = rotationState,
                orderItem = orderItem,
                airwayBillsState = airwayBillsState,
                onExpandClick = { expandedState = !expandedState },
                onConfirmOrderClick = onConfirmOrderClick,
                loadCargoViewModel = loadCargoViewModel,
                navController = navController
            )
        }
    }

}