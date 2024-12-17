package nl.ng.projectcargohubapp.location.service

interface LocationCallback {
    fun onLocationUpdate(lat: String, long: String)
}