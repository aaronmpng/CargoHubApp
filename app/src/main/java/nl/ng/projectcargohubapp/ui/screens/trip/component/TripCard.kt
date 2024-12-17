package nl.ng.projectcargohubapp.ui.screens.trip.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.common.Constants
import nl.ng.projectcargohubapp.common.Constants.formatTime
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.theme.Shapes
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import nl.ng.projectcargohubapp.ui.screens.trip.TripViewModel
import nl.ng.projectcargohubapp.strings.Strings
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun TripCard(
    item: Items,
    orderItems: List<OrderItems>,
    navController: NavController,
    viewModel: MainViewModel,
    tripViewModel: TripViewModel,
    checkInViewModel: CheckInViewModel
) {
    if (tripViewModel.checkItemPlannedStatus(item)) {
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
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(7.dp)
                        ) {
                            Text(text = tripViewModel.showCardDetails(orderItems, item))
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
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.Green),
                        onClick = {
                            tripViewModel.activateTripButton(navController, checkInViewModel, item, viewModel)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.activeTrip),
                            style = LocalTextStyle.current.copy(
                                fontSize = 15.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}