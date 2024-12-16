package com.delivery.tecnokargo.components

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.delivery.tecnokargo.MainActivity
import com.google.android.gms.location.LocationServices

fun GetCurrentLocation(
    context: Context,
    onSuccess: (Location) -> Unit,
    onError: () -> Unit
) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    onSuccess.invoke(location)
                } else {
                    onError.invoke()
                }
            }
    } else {
        AlertDialog.Builder(context)
            .setTitle("Permisos de Ubicacion")
            .setMessage("Es necesario conocer la ubicación actual para poder solicitar una grúa. Conceda los permisos e intente nuevamente.")
            .setPositiveButton("Sí") { _, _ ->
                ActivityCompat.requestPermissions(
                    context as MainActivity,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    ),
                    1
                )
            }
            .setNegativeButton("No", null)
            .show()
    }
}