package com.delivery.tecnokargo.components

import android.content.Context
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import androidx.camera.mlkit.vision.MlKitAnalyzer


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecognizerBarCode(
    onScan: (String) -> Unit, onDimissPermission: () -> Unit
) {

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    var requestPermisions by remember { mutableStateOf(false) }

    if (cameraPermissionState.status.isGranted) {
        requestPermisions = false
        CameraX() {
            onScan(it)
        }
    } else {
        requestPermisions = true
    }

    if (requestPermisions) {
        GenericAlertDialog(onDismiss = {
            requestPermisions = false
            onDimissPermission.invoke()
        },
            title = "Permission",
            message = "please provide camera permission",
            confirmButton = "Request permission",
            onAction = { cameraPermissionState.launchPermissionRequest() })
    }
}

@Composable
fun CameraX(
    onScan: (String) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            val previewView = PreviewView(context)
            startCamera(context, lifecycleOwner, previewView) { value ->
                if (value.isNotEmpty()) onScan(value)
            }
            previewView
        }, modifier = Modifier.fillMaxSize())
    }
}

private fun startCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    onScan: (String) -> Unit
) {
    val cameraController = LifecycleCameraController(context)
    val option = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC,
            Barcode.FORMAT_CODE_128,
            Barcode.FORMAT_CODE_39,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_UPC_A,
            Barcode.FORMAT_UPC_E,
            Barcode.FORMAT_ALL_FORMATS
        )
        .build()

    val barcodeScanner = BarcodeScanning.getClient(option)
    val mlkitAnalizer = MlKitAnalyzer(
        listOf(barcodeScanner),
        COORDINATE_SYSTEM_VIEW_REFERENCED,
        ContextCompat.getMainExecutor(context)
    ) { result ->
        val barcodeResults = result.getValue(barcodeScanner)
        val scanBarcodeValue = barcodeResults?.firstOrNull()?.rawValue ?: ""
        onScan(scanBarcodeValue)

    }
    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context), mlkitAnalizer
    )
    cameraController.bindToLifecycle(lifecycleOwner)
    previewView.controller = cameraController
}