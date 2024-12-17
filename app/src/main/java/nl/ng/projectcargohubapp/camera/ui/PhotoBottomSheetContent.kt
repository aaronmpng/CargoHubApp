package nl.ng.projectcargohubapp.camera.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import nl.ng.projectcargohubapp.camera.viewmodel.CameraViewModel

@Composable
fun PhotoBottomSheetContent(
    bitmaps: List<Bitmap>,
    onGalleryItemClick: (Bitmap) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        Log.d("bitmaps", "not empty")
        items(bitmaps) { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        // Pass the selected bitmap to the callback
                        onGalleryItemClick(bitmap)
                    }
            )
        }
    }
}

fun launchGalleryIntent(
    activity: ComponentActivity,
    cameraViewModel: CameraViewModel,
    onImageSelected: (Bitmap) -> Unit
) {
    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    galleryIntent.type = "image/*"

    val pickPhoto = activity.activityResultRegistry.register(
        "key",
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = result.data?.data

            // Convert the Uri to Bitmap
            val selectedBitmap =
                MediaStore.Images.Media.getBitmap(activity.contentResolver, selectedImageUri)

            selectedBitmap?.let {
                onImageSelected(it)
                cameraViewModel.onGalleryPhotoSelected(it)
            }
        }
    }
    pickPhoto.launch(galleryIntent)
}

