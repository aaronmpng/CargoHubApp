package nl.ng.projectcargohubapp.ui.screens.checkOut

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor() : ViewModel() {

    private var _button1 = MutableLiveData(false)
    var button1: LiveData<Boolean> = _button1

    private var _button2 = MutableLiveData(false)
    var button2: LiveData<Boolean> = _button2

    private var _button3 = MutableLiveData(false)
    var button3: LiveData<Boolean> = _button3

    private var _button4 = MutableLiveData(false)
    var button4: LiveData<Boolean> = _button4

    private var _button5 = MutableLiveData(false)
    var button5: LiveData<Boolean> = _button5

    private var _beforeLoading = MutableLiveData(true)
    var beforeLoading: LiveData<Boolean> = _beforeLoading


    fun saveButton1Value(newButtonValue: Boolean){
        _button1.value = newButtonValue
    }

    fun saveButton2Value(newButtonValue: Boolean){
        _button2.value = newButtonValue
    }

    fun saveButton3Value(newButtonValue: Boolean){
        _button3.value = newButtonValue
    }

    fun saveButton4Value(newButtonValue: Boolean){
        _button4.value = newButtonValue
    }

    fun saveButton5Value(newButtonValue: Boolean){
        _button5.value = newButtonValue
    }

    fun saveBeforeLoading(newValue: Boolean){
        _beforeLoading.value = newValue
    }

    var isButtonClicked1 by mutableStateOf(false)
    var isButtonClicked2 by mutableStateOf(false)
    var isButtonClicked4 by mutableStateOf(false)
    var isButtonClicked5 by mutableStateOf(false)
    var finishTrip by mutableStateOf(false)
    var finalizeTrip by mutableStateOf(false)

    var orderState: List<OrderItems> = emptyList()
    var tripState: List<Items> = emptyList()
    var i: Int = 0

    fun handleGateInButton() {
        if (beforeLoading.value == true) {
            saveButton1Value(true)
            isButtonClicked1 = !isButtonClicked1
        }
    }

    fun handleOfficeInButton(navController: NavController) {
        if (isButtonClicked1 && beforeLoading.value == true) {
            saveButton2Value(true)
            saveBeforeLoading(false)
            isButtonClicked2 = !isButtonClicked2
            navController.navigate(Screen.UnloadingScreen.route)
        }
    }

    fun handleOfficeOutButton() {
        if (beforeLoading.value == false) {
            saveButton4Value(true)
            isButtonClicked4 = !isButtonClicked4
        }
    }

    fun handleGateOutButton() {
        if (beforeLoading.value == false) {
            saveButton5Value(true)
            isButtonClicked5 = !isButtonClicked5
        }
    }

     fun shouldShowFinishTripButton(checkOutViewModel: CheckOutViewModel): Boolean {
        return !checkOutViewModel.beforeLoading.value!! &&
                checkOutViewModel.isButtonClicked4 &&
                checkOutViewModel.isButtonClicked5
    }

    fun toTripScreen(navController: NavController){
        navController.navigate(Screen.TripScreen.route)
        finishTrip = true
        finalizeTrip = true
    }
}
