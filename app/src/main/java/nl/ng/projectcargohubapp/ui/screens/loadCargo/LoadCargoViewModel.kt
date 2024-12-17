package nl.ng.projectcargohubapp.ui.screens.loadCargo

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.loadCargo.component.LoadingOrderDetails
import javax.inject.Inject

@HiltViewModel
class LoadCargoViewModel @Inject constructor() : ViewModel() {

    fun updateOrderStatus(viewModel: MainViewModel, orderState: List<OrderItems>) {
        viewModel.updateOrderStatus(orderState[index].orderID, Strings.loaded)
    }

    fun checkTripStatusActive(tripState: List<Items>, i: Int): Boolean {
        return (tripState[i - 1].status == Strings.active)
    }

    fun checkOrderIdCorrect(tripItem: Items, orderState: List<OrderItems>, o: Int): Boolean {
        return (tripItem.bookingID == orderState[o].bookingID)
    }

    private fun checkAirwayBillIdCorrect(
        airwayBillsState: List<AirwayBillsItems>,
        a: Int,
        orderItem: OrderItems
    ): Boolean {
        return (airwayBillsState[a].orderID == orderItem.orderID)
    }

    @Composable
    fun ShowCardDetail(
        airwayBillsState: List<AirwayBillsItems>,
        orderItem: OrderItems,
        navController: NavController
    ) {
        airwayBillsState.forEachIndexed { a, airwayBillItem ->
            checkAirwayBillIdCorrect(airwayBillsState, a, orderItem)
            if (airwayBillItem.orderID == orderItem.orderID) {
                LoadingOrderDetails(airwayBill = airwayBillItem, navController)
            }
        }
    }

    fun confirmLoading() {
        loadingState = !loadingState
        buttonColor = if (buttonColor == ColorPalette.Green) ColorPalette.Red else ColorPalette.Green
    }

    var buttonColor by mutableStateOf(ColorPalette.Red)
    var loadingState by mutableStateOf(false)
    var selected by   mutableStateOf(false)
    val index by  mutableStateOf(0)
}