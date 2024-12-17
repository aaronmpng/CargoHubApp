package nl.ng.projectcargohubapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import nl.ng.projectcargohubapp.location.service.LocationApp

@HiltAndroidApp
class AuthApp: Application() {

    override fun onCreate() {
        super.onCreate()
        LocationApp.init(this)
    }
}