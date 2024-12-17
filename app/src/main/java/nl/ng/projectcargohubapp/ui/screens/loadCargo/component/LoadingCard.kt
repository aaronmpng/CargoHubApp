package nl.ng.projectcargohubapp.ui.screens.loadCargo.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.AirwayBillsItems
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.theme.Shapes
import nl.ng.projectcargohubapp.ui.screens.loadCargo.LoadCargoViewModel
import nl.ng.projectcargohubapp.strings.Strings

@Composable
fun LoadingCard(
    expandedState: Boolean,
    rotationState: Float,
    orderItem: OrderItems,
    airwayBillsState: List<AirwayBillsItems>,
    onExpandClick: () -> Unit,
    onConfirmOrderClick: () -> Unit,
    loadCargoViewModel: LoadCargoViewModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 10.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        backgroundColor = ColorPalette.LightGray
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier
                        .weight(6f)
                        .padding(top = 8.dp, start = 8.dp),
                    text = "${stringResource(R.string.orderNr)}: ${orderItem.orderID}",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp, end = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                loadCargoViewModel.buttonColor,
                                shape = RoundedCornerShape(3.dp)
                            )
                            .padding(8.dp)
                            .width(135.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (loadCargoViewModel.loadingState) stringResource(R.string.loaded) else stringResource(
                                R.string.notLoaded
                            ),
                            color = ColorPalette.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            if (expandedState) {
                Row {
                    Text(
                        text = "${stringResource(R.string.airwayBills)} ",
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${stringResource(R.string.pieces)} ")
                }
                loadCargoViewModel.ShowCardDetail(airwayBillsState, orderItem, navController)
                LoadingConfirmButton(
                    onConfirmOrderClick = {
                        loadCargoViewModel.confirmLoading()
                        onConfirmOrderClick()
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
                    .background(ColorPalette.Gray)
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = { onExpandClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = stringResource(R.string.dropDownArrow)
                    )
                }

            }
        }
    }
}


