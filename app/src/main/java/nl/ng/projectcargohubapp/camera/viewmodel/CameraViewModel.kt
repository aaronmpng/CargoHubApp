package nl.ng.projectcargohubapp.camera.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.ng.projectcargohubapp.MainViewModel
import nl.ng.projectcargohubapp.ui.screens.activeTrip.ActiveTripViewModel
import java.io.ByteArrayOutputStream

class CameraViewModel : ViewModel() {

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

    fun onGalleryPhotoSelected(bitmap: Bitmap) {
        viewModelScope.launch {
            _bitmaps.emit(_bitmaps.value + bitmap)
        }
    }

    fun clearBitmaps() {
        _bitmaps.value = emptyList()
    }

    fun convertImageToJpeg(
        bitmapStateFlow: StateFlow<List<Bitmap>>,
        viewModel: MainViewModel,
        activeTripViewModel: ActiveTripViewModel
    ) {
        viewModelScope.launch {
            val bitmaps = bitmapStateFlow.value

            if (bitmaps.isNotEmpty()) {
                val outputStream = ByteArrayOutputStream()

                for (bitmap in bitmaps) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }

                val byteArray = outputStream.toByteArray()
                outputStream.close()
                uploadImageToServer(byteArray, viewModel, activeTripViewModel)
            }
        }
    }

    private fun uploadImageToServer(
        jpegByteArray: ByteArray,
        viewModel: MainViewModel,
        activeTripViewModel: ActiveTripViewModel
    ) {
        val type = "trip"
        val bookingID = activeTripViewModel.orderItems[activeTripViewModel.index]
        viewModel.postImage(jpegByteArray, type, bookingID.bookingID)
    }
}