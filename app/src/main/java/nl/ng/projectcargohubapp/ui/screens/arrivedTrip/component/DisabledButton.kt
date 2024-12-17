package nl.ng.projectcargohubapp.ui.screens.arrivedTrip.component

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun DisabledButton(text: String) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (text == "Active trip") ColorPalette.Blue else ColorPalette.LightGray
        ),
        onClick = {}
    ) {
        Text(text = text)
    }
}