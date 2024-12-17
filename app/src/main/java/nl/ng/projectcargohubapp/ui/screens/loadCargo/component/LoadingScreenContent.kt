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
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.model.OrderItems
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.strings.Strings
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.loadCargo.LoadCargoViewModel

@Composable
fun LoadingScreenContent(
    tripList: List<Items>,
    orderItems: List<OrderItems>,
    selected: Boolean,
    viewModel: MainViewModel,
    onConfirmOrderClick: () -> Unit,
    onLoadingFinishClick: () -> Unit,
    loadCargoViewModel: LoadCargoViewModel,
    navController: NavController
) {
    Column {
        tripList.forEachIndexed { i, tripItem ->
            if (loadCargoViewModel.checkTripStatusActive(tripList, i + 1)) {
                LoadingTripCard(
                    orderItems = orderItems,
                    tripItems = tripItem,
                    viewModel = viewModel,
                    onConfirmOrderClick = onConfirmOrderClick,
                    loadCargoViewModel = loadCargoViewModel,
                    navController = navController
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
                .padding(horizontal = 1.dp),
            horizontalAlignment = Alignment.End
        ) {
            val loadImage = stringResource(R.string.loadingImage)

            Button(
                onClick = {
                    navController.navigate(
                        Screen.FinishLoadingImageScreen.createRoute(
                            loadImage
                        )
                    )
                },
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorPalette.Blue,
                    contentColor = ColorPalette.White
                )
            ) {
                Text(text = stringResource(R.string.finishLoadingImage))
            }

            Spacer(modifier = Modifier.weight(1f))

            LoadingFinishButton(
                onLoadingFinishClick = onLoadingFinishClick,
                selected = selected
            )
        }
    }
}