package nl.ng.projectcargohubapp.ui.screens.unloadCargo.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel
import nl.ng.projectcargohubapp.ui.screens.unloadCargo.UnloadCargoViewModel

@Composable
fun UnloadingTripItem(
    navController: NavController,
    orderState: List<OrderItems>,
    tripList: List<Items>,
    viewModel: MainViewModel,
    tripIndex: Int,
    unloadCargoViewModel: UnloadCargoViewModel,
    checkOutViewModel: CheckOutViewModel
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    orderState.forEachIndexed { orderIndex, orderItem ->
        unloadCargoViewModel.checkTripIdCorrect(tripList, tripIndex, orderState, orderIndex)
        if (tripList[tripIndex - 1].bookingID == orderItem.bookingID) {

            viewModel.getAirwayBills(orderItem.orderID)
            val airwayBillsState by viewModel.airwayBillsList.collectAsState()

            UnloadingCard(
                expandedState = expandedState,
                rotationState = rotationState,
                onExpandClick = { expandedState = !expandedState },
                orderState = orderState,
                orderIndex = orderIndex,
                airwayBillsState = airwayBillsState,
                selected = unloadCargoViewModel.selected,
                onSelectedChange = { unloadCargoViewModel.selected = it },
                unloadCargoViewModel = unloadCargoViewModel,
                navController = navController
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
            .padding(horizontal = 1.dp),
        horizontalAlignment = Alignment.End
    ) {
        Button(
            onClick = { navController.navigate(Screen.FinishLoadingImageScreen.createRoute("Unloading Image")) },
            modifier = Modifier
                .width(250.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorPalette.Blue,
                contentColor = ColorPalette.White
            )
        ) {
            Text(stringResource(R.string.finishLoadingImage))
        }

        Spacer(modifier = Modifier.weight(1f))

        FinishTripButton(
            navController,
            orderState,
            tripList,
            unloadCargoViewModel.selected,
            tripIndex,
            checkOutViewModel
        )
    }
}