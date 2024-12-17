package nl.ng.projectcargohubapp.ui.screens.arrivedTrip

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.common.Constants
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.strings.Strings
import javax.inject.Inject

@HiltViewModel
class ArrivedTripViewModel @Inject constructor() : ViewModel() {

    fun checkIfStatusActive(tripState: List<Items>, i: Int): Boolean {
        return (tripState[i - 1].status == Strings.active)
    }

    fun updateOrderStatus(viewModel: MainViewModel, orderState: List<OrderItems>, i: Int) {
        viewModel.updateOrderStatus(orderState[i].orderID, Strings.loaded)
    }

    fun updateTripStatus(viewModel: MainViewModel, tripState: List<Items>, i: Int) {
        viewModel.updateTripStatus(tripState[i - 1].bookingID, Strings.closed)
    }

    private fun checkIfBookingIdMatch(orderItems: List<OrderItems>, item: Items, o: Int): Boolean {
        return item.bookingID == orderItems[o].bookingID
    }

    fun showCardDetails(item: Items): String {
        var pickupTimeSlot: String = ""
        orderItems.forEachIndexed { orderIndex, orderItem ->
            if (checkIfBookingIdMatch(
                    orderItems,
                    item,
                    orderIndex
                )
            ) {

                val pickupTime = Constants.formatTime(orderItem.pickupTime)
                pickupTimeSlot =
                    "${Strings.pickupTime}: $pickupTime ${Strings.timeslot}: ${orderItem.timeSlot}"
            }
        }
        return pickupTimeSlot
    }

    private var _orderItems = mutableListOf<OrderItems>()
    var orderItems: List<OrderItems> = _orderItems

    fun saveOrderItems(newOrderItemsValue: List<OrderItems>) {
        _orderItems.clear()
        _orderItems.addAll(newOrderItemsValue)
    }
}