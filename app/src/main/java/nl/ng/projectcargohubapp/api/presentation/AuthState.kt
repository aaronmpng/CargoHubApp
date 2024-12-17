package nl.ng.projectcargohubapp.api.presentation

data class AuthState(
    val isLoading: Boolean = false,
    val signInEmail: String = "",
    val signInPassword: String = ""
)
