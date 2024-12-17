package nl.ng.projectcargohubapp.ui.screens.closedTrip.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun ClosedTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.closedTrips),
                color = ColorPalette.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        },
        backgroundColor = ColorPalette.Blue
    )
}