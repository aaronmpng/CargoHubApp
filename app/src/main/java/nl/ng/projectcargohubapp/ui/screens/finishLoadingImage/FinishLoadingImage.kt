package nl.ng.projectcargohubapp.ui.screens.finishLoadingImage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.camera.viewmodel.CameraViewModel
import nl.ng.projectcargohubapp.navigation.Screen
import nl.ng.projectcargohubapp.theme.ColorPalette
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel

@Composable
fun AddImage(
    navController: NavController,
    cameraViewModel: CameraViewModel,
    title: String,
    viewModel: MainViewModel,
    activeTripViewModel: ActiveTripViewModel
) {
    var commentText by remember { mutableStateOf("") }
    val bitmaps by cameraViewModel.bitmaps.collectAsState()

    DisposableEffect(Unit) {
        onDispose {
            cameraViewModel.clearBitmaps()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        color = ColorPalette.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back),
                            tint = ColorPalette.White
                        )
                    }
                },
                backgroundColor = ColorPalette.Blue
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                // Main content goes here
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    if (bitmaps.isNotEmpty()) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(16.dp),
                            modifier = Modifier
                        ) {
                            items(bitmaps) { bitmap ->
                                Image(
                                    bitmap = bitmap.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                )
                            }
                        }
                    }

                    Text(stringResource(R.string.comment))
                    OutlinedTextField(
                        value = commentText,
                        onValueChange = { commentText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(color = Color.Gray),
                        textStyle = MaterialTheme.typography.body1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                            }
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { navController.navigate(Screen.BottomScaffold.route) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.Green)
                        ) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(stringResource(R.string.openCamera))
                        }

                        Button(
                            onClick = {
                                cameraViewModel.convertImageToJpeg(cameraViewModel.bitmaps, viewModel, activeTripViewModel)
                                cameraViewModel.clearBitmaps()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = ColorPalette.Green)
                        ) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(stringResource(R.string.uploadImage))
                        }
                    }
                }
            }
        }
    )
}


