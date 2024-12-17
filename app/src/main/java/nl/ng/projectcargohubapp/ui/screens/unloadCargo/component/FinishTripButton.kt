package nl.ng.projectcargohubapp.ui.screens.unloadCargo.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel

@Composable
fun FinishTripButton(
    navController: NavController,
    orderState: List<OrderItems>,
    tripList: List<Items>,
    selected: Boolean,
    tripIndex: Int, checkOutViewModel: CheckOutViewModel
) {
    Row() {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorPalette.Green,
                contentColor = ColorPalette.White
            ),
            onClick = {
                navController.navigate(Screen.ArrivedTripScreen.route)
                checkOutViewModel.saveButton3Value(true)

                checkOutViewModel.tripState = tripList
                checkOutViewModel.orderState = orderState
                checkOutViewModel.i = tripIndex
            },
            enabled = selected,
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(end = 1.dp, bottom = 16.dp)
                .fillMaxWidth()
                .align(Alignment.Bottom),
        ) {
            Text(text = "Finish unloading")
        }
    }
}