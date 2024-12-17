package nl.ng.projectcargohubapp.ui.screens.checkIn

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.location.service.LocationCallback
import nl.ng.projectcargohubapp.location.service.LocationService
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel

@Composable
fun CheckInProcessButtons(
    navController: NavController,
    checkInViewModel: CheckInViewModel,
    applicationContext: Context,
    viewModel: MainViewModel,
    activeTripViewModel: ActiveTripViewModel
) {

    DisposableEffect(Unit) {
        onDispose {
            Intent(applicationContext, LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                applicationContext.startService(this)
            }
        }
    }
    val activity = LocalContext.current as ComponentActivity
    val context = LocalContext.current
    val callback = object : LocationCallback {
        override fun onLocationUpdate(lat: String, long: String) {
            checkInViewModel.updateLocation(viewModel, lat, long)
        }
    }
    LocationService.setCallback(callback)

    if (!checkInViewModel.startTrip && checkInViewModel.startLoading) {
        Column {
            CheckInProcess(stringResource(R.string.gateIn), {
                checkInViewModel.handleGateInButton()
            }, checkInViewModel.isButtonClicked1)

            CheckInProcess(stringResource(R.string.officeIn), {
                checkInViewModel.handleOfficeInButton(navController, activeTripViewModel)
            }, checkInViewModel.isButtonClicked2)

            checkInViewModel.button3.value?.let {
                CheckInProcess(
                    stringResource(R.string.loadOrders),
                    {},
                    it
                )
            }

            CheckInProcess(
                stringResource(R.string.officeOut), {
                    checkInViewModel.handleOfficeOutButton()
                },
                checkInViewModel.isButtonClicked4
            )

            CheckInProcess(
                stringResource(R.string.gateOut), {
                    checkInViewModel.handleGateOutButton()
                },
                checkInViewModel.isButtonClicked5
            )
        }

        val showStartTripButton =
            checkInViewModel.beforeLoading.value == false && checkInViewModel.isButtonClicked4 && checkInViewModel.isButtonClicked5
        if (showStartTripButton) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorPalette.Green,
                        contentColor = ColorPalette.White
                    ),
                    onClick = {
                        checkInViewModel.handleStartTripButtonClick(activeTripViewModel)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(70.dp)
                        .padding(end = 1.dp, bottom = 16.dp)
                ) {
                    Text(text = stringResource(R.string.startTrip))
                }
            }
        }
    }

    var isStartLocation by remember { mutableStateOf(checkInViewModel.startLocation) }
    LaunchedEffect(checkInViewModel.startLocation) {
        isStartLocation = checkInViewModel.startLocation
    }
    if (activeTripViewModel.arrivedDestination) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            checkInViewModel.locationPermission(activity)
            checkInViewModel.LaunchPermissionEffect(activity)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isStartLocation) ColorPalette.Orange else ColorPalette.Green,
                    contentColor = ColorPalette.White
                ),
                onClick = {
                    checkInViewModel.startLocation(applicationContext, context)
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
            ) {
                Text(
                    text = if (isStartLocation) stringResource(R.string.pause) else stringResource(
                        R.string.start
                    )
                )
            }
        }
    }
}