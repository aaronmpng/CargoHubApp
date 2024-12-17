package nl.ng.projectcargohubapp.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.camera.ui.BottomScaffold
import nl.ng.projectcargohubapp.camera.viewmodel.CameraViewModel
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripScreen
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.ArrivedTripScreen
import nl.ng.projectcargohubapp.ui.screens.arrivedTrip.ArrivedTripViewModel
import nl.ng.projectcargohubapp.ui.screens.checkIn.CheckInViewModel
import nl.ng.projectcargohubapp.ui.screens.checkOut.CheckOutViewModel
import nl.ng.projectcargohubapp.ui.screens.closedTrip.ClosedTripScreen
import nl.ng.projectcargohubapp.ui.screens.finishLoadingImage.AddImage
import nl.ng.projectcargohubapp.ui.screens.loadCargo.LoadingScreen
import nl.ng.projectcargohubapp.ui.screens.login.LoginScreen
import nl.ng.projectcargohubapp.ui.screens.login.LoginViewModel
import nl.ng.projectcargohubapp.ui.screens.trip.TripScreen
import nl.ng.projectcargohubapp.ui.screens.trip.TripViewModel
import nl.ng.projectcargohubapp.ui.screens.unloadCargo.UnloadingScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    checkInViewModel: CheckInViewModel = hiltViewModel(),
    checkOutViewModel: CheckOutViewModel = hiltViewModel(),
    cameraViewModel: CameraViewModel = hiltViewModel(),
    activeTripViewModel: ActiveTripViewModel = hiltViewModel(),
    arrivedTripViewModel: ArrivedTripViewModel = hiltViewModel(),
    viewModel: MainViewModel = hiltViewModel(),
    tripViewModel: TripViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    applicationContext: Context
) {

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, viewModel, loginViewModel)
        }

        composable(route = Screen.TripScreen.route) {
            TripScreen(navController = navController, checkInViewModel, activeTripViewModel, arrivedTripViewModel, viewModel, tripViewModel)
        }

        composable(route = Screen.ActiveTripScreen.route) {
            ActiveTripScreen(navController = navController, checkInViewModel, applicationContext, activeTripViewModel, viewModel)
        }

        composable(route = Screen.LoadingScreen.route) {
            LoadingScreen(navController = navController, checkInViewModel, applicationContext, activeTripViewModel, viewModel)
        }

        composable(route = Screen.ArrivedTripScreen.route) {
            ArrivedTripScreen(navController = navController, checkOutViewModel, arrivedTripViewModel)
        }

        composable(route = Screen.UnloadingScreen.route) {
            UnloadingScreen(navController = navController, checkOutViewModel)
        }

        composable(route = Screen.ClosedTripScreen.route) {
            ClosedTripScreen(navController = navController, arrivedTripViewModel, viewModel)
        }

        composable(route = Screen.FinishLoadingImageScreen.route) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("param") ?: ""
            AddImage(navController = navController, cameraViewModel, title, viewModel, activeTripViewModel)
        }

        composable(route = Screen.BottomScaffold.route) {
            BottomScaffold(
                navController = navController,
                applicationContext,
                cameraViewModel
            )
        }
    }
}

