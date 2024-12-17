package nl.ng.projectcargohubapp.ui.screens.arrivedTrip.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.strings.Strings

@Composable
fun TripButtonsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(11.dp)
    ) {
        DisabledButton(text = stringResource(R.string.planned))
        DisabledButton(text = stringResource(R.string.activeTrip))
        DisabledButton(text = stringResource(R.string.closedTrips))
    }
}