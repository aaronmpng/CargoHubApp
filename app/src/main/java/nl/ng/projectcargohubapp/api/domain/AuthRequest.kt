package nl.ng.projectcargohubapp.api.domain

data class AuthRequest(
    val email: String,
    val password: String
)