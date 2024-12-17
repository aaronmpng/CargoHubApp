package nl.ng.projectcargohubapp.camera.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionsUtil {
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA
        )

        fun requestCameraPermissions(activity: Activity) {
            if (!hasRequiredPermissions(activity)) {
                ActivityCompat.requestPermissions(
                    activity, CAMERAX_PERMISSIONS, 0
                )
            }
        }

        fun hasRequiredPermissions(context: Context): Boolean {
            return CAMERAX_PERMISSIONS.all {
                ContextCompat.checkSelfPermission(
                    context, it
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
    }
}
