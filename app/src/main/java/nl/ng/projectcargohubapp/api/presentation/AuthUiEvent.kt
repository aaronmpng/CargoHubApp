package nl.ng.projectcargohubapp.api.presentation

sealed class AuthUiEvent {

    data class SignInEmailChanged(val value: String): AuthUiEvent()
    data class SignInPasswordChanged(val value: String): AuthUiEvent()
    object SignIn: AuthUiEvent()
}
