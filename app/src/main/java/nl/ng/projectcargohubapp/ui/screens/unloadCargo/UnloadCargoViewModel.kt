package nl.ng.projectcargohubapp.ui.screens.unloadCargo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.theme.ColorPalette
import javax.inject.Inject

@HiltViewModel
class UnloadCargoViewModel @Inject constructor() : ViewModel() {

    @Composable
    fun checkTripStatusActive(tripState: List<Items>, tripIndex: Int): Boolean{
        return (tripState[tripIndex - 1].status == stringResource(R.string.activeTrip))
    }

    fun checkTripIdCorrect(tripState: List<Items>, tripIndex: Int, orderState: List<OrderItems>, o:Int): Boolean{
        return (tripState[tripIndex - 1].bookingID == orderState[o].bookingID)
    }

    fun checkAirwayBillStateIdCorrect(airwayBillsState: List<AirwayBillsItems>, airwayBillIndex: Int, orderState: List<OrderItems>, orderIndex: Int): Boolean{
        return (airwayBillsState[airwayBillIndex].orderID == orderState[orderIndex].orderID)
    }

    fun confirmLoading() {
        unloadingState = !unloadingState
        buttonColor = if (buttonColor == ColorPalette.Green) ColorPalette.Red else ColorPalette.Green
    }
    var buttonColor by mutableStateOf(ColorPalette.Red)
    var unloadingState by mutableStateOf(false)
    var selected by mutableStateOf(false)
}