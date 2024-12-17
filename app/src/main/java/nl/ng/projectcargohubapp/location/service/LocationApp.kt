package nl.ng.projectcargohubapp.location.service

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import nl.ng.projectcargohubapp.strings.Strings

class LocationApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init(this)
    }

    companion object {
        fun init(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    Strings.location,
                    Strings.Location,
                    NotificationManager.IMPORTANCE_LOW
                )
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}
