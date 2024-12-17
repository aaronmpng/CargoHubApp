package nl.ng.projectcargohubapp.ui.screens.unloadCargo.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.unloadCargo.UnloadCargoViewModel

@Composable
fun DisplayAirwayBills(
    airwayBillsState: List<AirwayBillsItems>,
    orderState: List<OrderItems>,
    unloadCargoViewModel: UnloadCargoViewModel,
    o: Int,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        airwayBillsState.forEachIndexed { a, airwayBillItem ->
            unloadCargoViewModel.checkAirwayBillStateIdCorrect(airwayBillsState, a, orderState, o)
            Text(
                text = airwayBillItem.airwayBillID,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(50.dp))
            Text(text = airwayBillItem.pcs)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Screen.FinishLoadingImageScreen.createRoute("Report damage")) },
                modifier = Modifier
                    .width(160.dp)
                    .padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorPalette.Orange,
                    contentColor = ColorPalette.White
                )
            ) {
                Text(text = "Report damage")
            }
        }
    }
}