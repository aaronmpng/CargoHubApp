package nl.ng.projectcargohubapp.ui.screens.activeTrip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.common.Constants
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.theme.ColorPalette

class ActiveTripViewModel : ViewModel() {

    fun checkIfStatusActive(state: List<Items>, i: Int): Boolean{
        return (state[i - 1].status == Strings.active)
    }

    private fun checkIfBookingIdMatch(orderItems: List<OrderItems>, item: Items, o: Int): Boolean {
        return item.bookingID == orderItems[o].bookingID
    }

    private var _orderItems = mutableListOf<OrderItems>()
    var orderItems: List<OrderItems> = _orderItems

    fun saveOrderItems(newOrderItemsValue: List<OrderItems>) {
        _orderItems.clear()
        _orderItems.addAll(newOrderItemsValue)
    }

    fun showCardDetails(item: Items): String{
        var pickupTimeSlot: String = ""
        orderItems.forEachIndexed { indexx, orderItem ->
            if (checkIfBookingIdMatch(orderItems, item, indexx)) {

                val pickupTime = Constants.formatTime(orderItem.pickupTime)
                index = indexx
                pickupTimeSlot =
                    "${Strings.pickupTime}: $pickupTime ${Strings.timeslot}: ${orderItem.timeSlot}"
            }
        }
        return pickupTimeSlot
    }

    fun screenHandling(navController: NavController, item: Items, viewModel: MainViewModel) {
        if (!start) {
            navController.navigate(Screen.TripScreen.route)
            viewModel.updateTripStatus(item.bookingID, Strings.planned)
        } else {
            arrivedDestination = false
            navController.navigate(Screen.ArrivedTripScreen.route)
        }
    }

    fun getButtonProperties(item: Items): ButtonProperties {
        return if (!start) {
            ButtonProperties(
                backgroundColor = ColorPalette.Red,
                buttonText = "Cancel"
            )
        } else {
            ButtonProperties(
                backgroundColor = ColorPalette.Green,
                buttonText = "Arrive at destination"
            )
        }
    }

    data class ButtonProperties(
        val backgroundColor: Color,
        val buttonText: String
    )

    var index by mutableStateOf(0)
    var start by mutableStateOf(false)
    var buttonAppear by mutableStateOf(true)
    var arrivedDestination by mutableStateOf(false)
}