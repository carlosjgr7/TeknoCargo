package com.delivery.tecnokargo.map.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun MapScreen() {
    val miami = LatLng(25.7617, -80.1918)
    val cameraState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(miami, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraState
    ){
        Marker(
            state = MarkerState(position = miami),
            title = "miami",
            snippet = "Welcome to miami!"
        )
    }
}