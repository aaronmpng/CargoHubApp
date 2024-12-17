package nl.ng.projectcargohubapp.ui.screens.login
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.api.presentation.AuthUiEvent
import nl.ng.projectcargohubapp.model.Items
import nl.ng.projectcargohubapp.strings.Strings
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel()  {

    fun loginUser(viewModel:MainViewModel){
        viewModel.onEvent(AuthUiEvent.SignIn)
    }
}