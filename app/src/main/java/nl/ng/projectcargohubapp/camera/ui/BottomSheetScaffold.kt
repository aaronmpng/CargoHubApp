package nl.ng.projectcargohubapp.camera.ui

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.camera.util.PermissionsUtil
import nl.ng.projectcargohubapp.camera.viewmodel.CameraViewModel
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomScaffold(
    navController: NavController,
    applicationContext: Context,
    cameraViewModel: CameraViewModel
) {
    val activity = LocalContext.current as ComponentActivity

    LaunchedEffect(true) {
        if (!PermissionsUtil.hasRequiredPermissions(activity)) {
            PermissionsUtil.requestCameraPermissions(activity)
        }
    }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val controller = remember {
        LifecycleCameraController(applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }

    val bitmaps by cameraViewModel.bitmaps.collectAsState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            PhotoBottomSheetContent(
                bitmaps = bitmaps,
                onGalleryItemClick = { uri ->
                    // Handle the selected image Uri
                    // You can load the image using the Uri or perform other operations
                    // For simplicity, I'm just printing the Uri here
                    Log.d("camera", "Selected image Uri: $uri")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier
                    .fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {
                    launchGalleryIntent(activity, cameraViewModel) { uri ->
                        Log.d("camera", "Selected image Uri: $uri")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = stringResource(R.string.openGallery)
                    )
                }
                IconButton(onClick = {
                    takePhoto(
                        controller = controller,
                        applicationContext = applicationContext,
                        onPhotoTaken = cameraViewModel::onTakePhoto
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.Camera,
                        contentDescription = stringResource(R.string.takePhoto)
                    )
                }
            }
        }
    }
}

private fun takePhoto(
    controller: LifecycleCameraController,
    applicationContext: Context,
    onPhotoTaken: KFunction1<Bitmap, Unit>
) {
    controller.takePicture(
        ContextCompat.getMainExecutor(applicationContext),
        object : OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = image.toBitmapWithRotation(matrix)

                // Save the photo to the device
                val savedUri = savePhotoToStorage(rotatedBitmap, applicationContext)
//                onPhotoTaken(savedUri)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("camera", "Couldn't take photo: ", exception)
            }
        }
    )
}

private fun ImageProxy.toBitmapWithRotation(matrix: Matrix): Bitmap {
    val buffer = planes[0].buffer
    buffer.rewind()
    val bytes = ByteArray(buffer.capacity())
    buffer.get(bytes)
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, BitmapFactory.Options()).apply {
        // Apply rotation matrix
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
}

private fun savePhotoToStorage(bitmap: Bitmap, context: Context): Uri {
    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }

    val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    uri?.let { outputStream ->
        contentResolver.openOutputStream(outputStream)?.use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
    }

    return uri ?: Uri.EMPTY
}