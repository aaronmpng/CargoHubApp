package nl.ng.projectcargohubapp.ui.screens.checkOut

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInProcess

@Composable
fun CheckOutProcessButtons(
    navController: NavController,
    checkOutViewModel: CheckOutViewModel
) {
    if (!checkOutViewModel.finishTrip) {
        Column {
            CheckOutProcess(stringResource(R.string.gateIn), {
                checkOutViewModel.handleGateInButton()
            }, checkOutViewModel.isButtonClicked1)

            CheckOutProcess(stringResource(R.string.officeIn), {
                checkOutViewModel.handleOfficeInButton(navController)
            }, checkOutViewModel.isButtonClicked2)

            checkOutViewModel.button3.value?.let { CheckInProcess(stringResource(R.string.loadOrders), {}, it) }

            CheckOutProcess(
                stringResource(R.string.officeOut), {
                    checkOutViewModel.handleOfficeOutButton()
                },
                checkOutViewModel.isButtonClicked4
            )

            CheckOutProcess(
                stringResource(R.string.gateOut), {
                    checkOutViewModel.handleGateOutButton()
                },
                checkOutViewModel.isButtonClicked5
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (checkOutViewModel.shouldShowFinishTripButton(checkOutViewModel)) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorPalette.Green,
                        contentColor = ColorPalette.White
                    ),
                    onClick = {
                       checkOutViewModel.toTripScreen(navController)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .height(70.dp)
                        .padding(end = 1.dp, bottom = 16.dp)
                ) {
                    Text(stringResource(R.string.finishTrip))
                }
            }
        }
    }
}