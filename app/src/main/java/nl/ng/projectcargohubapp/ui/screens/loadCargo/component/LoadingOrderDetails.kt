package nl.ng.projectcargohubapp.ui.screens.loadCargo.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun LoadingOrderDetails(airwayBill: AirwayBillsItems, navController: NavController) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Text(
            text = airwayBill.airwayBillID,
            modifier = Modifier
                .padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.width(50.dp))
        Text(text = airwayBill.pcs)

        Spacer(modifier = Modifier.weight(1f))

        val reportDamage = stringResource(R.string.reportDamage)
        Button(
            onClick = {
                navController.navigate(
                    Screen.FinishLoadingImageScreen.createRoute(
                        reportDamage
                    )
                )
            },
            modifier = Modifier
                .width(160.dp)
                .padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorPalette.Orange,
                contentColor = ColorPalette.White
            )
        ) {
            Text(text = stringResource(R.string.reportDamage))
        }
    }
}
