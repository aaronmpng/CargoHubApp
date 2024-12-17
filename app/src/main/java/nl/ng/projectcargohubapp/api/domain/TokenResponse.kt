package nl.ng.projectcargohubapp.api.domain

data class TokenResponse(
    var accessToken: String,
    var tokenType: String,
    var expiresIn: String
)
