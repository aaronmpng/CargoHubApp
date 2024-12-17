package nl.ng.projectcargohubapp.ui.screens.checkIn

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.camera.util.PermissionsUtil
import nl.ng.projectcargohubapp.location.service.LocationApp
import nl.ng.projectcargohubapp.location.service.LocationService
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import javax.inject.Inject

@HiltViewModel
class CheckInViewModel @Inject constructor() : ViewModel() {

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


    private fun saveButton1Value(newButtonValue: Boolean) {
        _button1.value = newButtonValue
    }

    private fun saveButton2Value(newButtonValue: Boolean) {
        _button2.value = newButtonValue
    }

    fun saveButton3Value(newButtonValue: Boolean) {
        _button3.value = newButtonValue
    }

    private fun saveButton4Value(newButtonValue: Boolean) {
        _button4.value = newButtonValue
    }

    fun saveButton5Value(newButtonValue: Boolean) {
        _button5.value = newButtonValue
    }

    private fun saveBeforeLoading(newValue: Boolean) {
        _beforeLoading.value = newValue
    }

    var isButtonClicked1 by mutableStateOf(false)
    var isButtonClicked2 by mutableStateOf(false)
    var isButtonClicked4 by mutableStateOf(false)
    var isButtonClicked5 by mutableStateOf(false)
    var startTrip by mutableStateOf(false)
    var arrivedAtDestination by mutableStateOf(false)
    var startLoading by mutableStateOf(false)
    var startLocation by mutableStateOf(false)

    fun updateLocation(viewModel: MainViewModel, lat: String, long: String) {
        val bookingID = "AMS0026478590"
        viewModel.updateLocation(bookingID, lat, long)
    }

    fun handleGateInButton() {
        if (beforeLoading.value == true) {
            saveButton1Value(true)
            isButtonClicked1 = !isButtonClicked1
        }
    }

    fun handleOfficeInButton(navController: NavController, activeTripViewModel: ActiveTripViewModel) {
        if (isButtonClicked1 && beforeLoading.value == true) {
            saveButton2Value(true)
            saveBeforeLoading(false)
            isButtonClicked2 = !isButtonClicked2
            activeTripViewModel.buttonAppear = false
            navController.navigate(Screen.LoadingScreen.route)
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

    fun handleStartTripButtonClick(activeTripViewModel: ActiveTripViewModel) {
        startTrip = true
        activeTripViewModel.arrivedDestination = true
        activeTripViewModel.start = true
        activeTripViewModel.buttonAppear = true
    }

    fun locationPermission(activity: ComponentActivity){
        LocationApp.init(activity)
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )
    }

    @Composable
    fun LaunchPermissionEffect(activity: ComponentActivity){
        LaunchedEffect(true) {
            if (!PermissionsUtil.hasRequiredPermissions(activity)) {
                PermissionsUtil.requestCameraPermissions(activity)
            }
        }
    }

    fun startLocation(applicationContext: Context, context: Context){
        startLocation = !startLocation
        val action = if (startLocation) {
            LocationService.ACTION_START
        } else {
            LocationService.ACTION_STOP
        }
        Intent(applicationContext, LocationService::class.java).apply {
            this.action = action
            context.startService(this)
        }
        startLoading = !startLocation
    }
}