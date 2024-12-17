package nl.ng.projectcargohubapp.ui.screens.closedTrip.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.theme.Shapes
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.ArrivedTripViewModel

@Composable
fun ClosedTripCard(item: Items, arrivedTripViewModel: ArrivedTripViewModel) {
    val expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    if (item.status == stringResource(R.string.closed)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = Shapes.medium,
            backgroundColor = ColorPalette.LightGray
        ) {
            Row() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                ) {
                    Row() {
                        Text(
                            text = item.airportPickup,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 40.dp)
                                .align(Alignment.CenterVertically),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "->",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.airportDrop,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 30.dp)
                                .align(Alignment.CenterVertically),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Left section
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(7.dp)
                        ) {
                            Text(text = arrivedTripViewModel.showCardDetails(item))
                        }
                        Divider(
                            modifier = Modifier
                                .width(9.dp)
                                .height(50.dp)
                                .padding(4.dp),
                            color = Color.Black
                        )
                        // Right section
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(7.dp)
                        ) {
                            Text("${stringResource(R.string.handler)}: ${item.pickupHandler}")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(top = 5.dp))
    }
}