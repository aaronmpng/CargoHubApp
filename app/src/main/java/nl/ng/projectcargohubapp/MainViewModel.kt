package nl.ng.projectcargohubapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nl.ng.projectcargohubapp.api.domain.AuthRepository
import nl.ng.projectcargohubapp.api.domain.AuthResult
import nl.ng.projectcargohubapp.api.presentation.AuthState
import nl.ng.projectcargohubapp.api.presentation.AuthUiEvent
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(AuthState())

    var tripList = MutableStateFlow(emptyList<Items>())
    val listState: StateFlow<List<Items>>
        get() = tripList

    var orderList = MutableStateFlow(emptyList<OrderItems>())
    val orderListState: StateFlow<List<OrderItems>>
        get() = orderList

    var airwayBillsList = MutableStateFlow(emptyList<AirwayBillsItems>())

    private val resultChannel = Channel<AuthResult<String>>()
    val authResults = resultChannel.receiveAsFlow()

    companion object {
        var data: String = ""
    }

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.SignInEmailChanged -> {
                state = state.copy(signInEmail = event.value)
            }
            is AuthUiEvent.SignInPasswordChanged -> {
                state = state.copy(signInPassword = event.value)
            }
            is AuthUiEvent.SignIn -> {
                signIn()
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            val result = repository.signIn(
                email = state.signInEmail,
                password = state.signInPassword
            )
            resultChannel.send(result)
            data = result.data.toString()
        }
    }

    fun getTrips() {
        viewModelScope.launch {
            val result = repository.getTrips(
                date = "2023-11-09"
            )
            tripList.value = result
        }
    }

    fun getOrders() {
        viewModelScope.launch {
            val result = repository.getOrders()
            orderList.value = result
        }
    }

    fun getAirwayBills(orderID: String) {
        viewModelScope.launch {
            val result = repository.getAirwayBills(
                orderID = orderID
            )
            airwayBillsList.value = result
        }
    }

    fun updateOrderStatus(orderID: String, status: String) {
        viewModelScope.launch {
            val result = repository.updateOrderStatus(
                orderID = orderID,
                status = status
            )
        }
    }

    fun updateTripStatus(bookingId: String, status: String) {
        viewModelScope.launch {
            val result = repository.updateTripStatus(
                BookingID = bookingId,
                Status = status
            )
        }
    }

    fun postImage(jpegByteArray: ByteArray, type: String, bookingID: String) {
        viewModelScope.launch {
            Log.d("camera", "[mainViewModel] postImage in")
            val result = repository.postImage(
                jpegByteArray = jpegByteArray,
                type = type,
                bookingID = bookingID
            )
        }
    }

    fun updateLocation(bookingID: String, lat: String, long: String) {
        viewModelScope.launch {
            viewModelScope.launch {
                val result = repository.updateLocation(
                    bookingID = bookingID,
                    lat = lat,
                    long = long
                )
            }
        }
    }
}

