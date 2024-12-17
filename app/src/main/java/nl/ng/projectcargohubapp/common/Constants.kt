package nl.ng.projectcargohubapp.common

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    private const val TIME_FORMAT_PATTERN = "HH:mm:ss"
    private const val TIME_FORMAT_PATTERN_TARGET = "HH:mm"

    fun formatTime(pickupTime: String): String {
        val originalFormat = SimpleDateFormat(TIME_FORMAT_PATTERN, Locale.getDefault())
        val targetFormat = SimpleDateFormat(TIME_FORMAT_PATTERN_TARGET, Locale.getDefault())

        val parsedTime = originalFormat.parse(pickupTime)
        return targetFormat.format(parsedTime)
    }
}
