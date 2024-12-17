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
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun LoadingFinishButton(onLoadingFinishClick: () -> Unit, selected: Boolean) {

    Row() {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ColorPalette.Green,
                contentColor = ColorPalette.White
            ),
            onClick = { onLoadingFinishClick() },
            enabled = selected,
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
                .padding(end = 1.dp, bottom = 16.dp)
                .fillMaxWidth()
                .align(Alignment.Bottom),
        ) {
            Text(text = stringResource(R.string.finishLoading))
        }
    }
}