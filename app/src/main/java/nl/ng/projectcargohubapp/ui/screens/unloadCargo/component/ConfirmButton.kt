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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun ConfirmButton(selected: Boolean, onSelectedChange: (Boolean) -> Unit) {
    Row() {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorPalette.Green,
                contentColor = ColorPalette.White
            ),
            onClick = { onSelectedChange(!selected) },
            modifier = Modifier
                .width(160.dp)
                .padding(end = 8.dp)
                .padding(bottom = 4.dp)
        ) {
            Text(text = "Confirm order")
        }
    }
}
