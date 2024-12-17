package nl.ng.projectcargohubapp.ui.screens.trip

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.common.Constants
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import javax.inject.Inject

@HiltViewModel
class TripViewModel @Inject constructor() : ViewModel() {

    fun checkItemPlannedStatus(items: Items): Boolean {
        return items.status == Strings.planned
    }

    fun updateTripStatus(bookingId: String, viewModel: MainViewModel) {
        viewModel.updateTripStatus(bookingId, Strings.active)
    }

    fun checkOrderState(orderState: List<OrderItems>, item: Items, o: Int): Boolean {
        return item.bookingID == orderState[o].bookingID
    }

    fun showCardDetails(orderItems: List<OrderItems>, item: Items): String {
        var pickupTimeSlot: String = ""
        orderItems.forEachIndexed { o, orderItem ->
            if (checkOrderState(orderItems, item, o)) {

                val pickupTime = Constants.formatTime(orderItem.pickupTime)
                pickupTimeSlot =
                    "${Strings.pickupTime}: $pickupTime ${Strings.timeslot}: ${orderItem.timeSlot}"
            }
        }
        return pickupTimeSlot
    }

    fun activateTripButton(
        navController: NavController,
        checkInViewModel: CheckInViewModel,
        item: Items,
        viewModel: MainViewModel
    ) {
        navController.navigate(Screen.ActiveTripScreen.route)
        updateTripStatus(item.bookingID, viewModel)
        checkInViewModel.startLoading = true
    }
}
