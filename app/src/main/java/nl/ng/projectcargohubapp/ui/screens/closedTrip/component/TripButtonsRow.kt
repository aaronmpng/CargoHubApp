package nl.ng.projectcargohubapp.ui.screens.closedTrip.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun TripButtonsRow(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.LightGray),
            onClick = { navController.navigate(Screen.TripScreen.route) }
        ) {
            Text(text = stringResource(R.string.planned))
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.LightGray),
            onClick = {navController.navigate(Screen.ActiveTripScreen.route) }
        ) {
            Text(text = stringResource(R.string.activeTrip))
        }
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.Blue),
            onClick = { navController.navigate(Screen.ClosedTripScreen.route) }
        ) {
            Text(text = stringResource(R.string.closedTrips))
        }
    }
}