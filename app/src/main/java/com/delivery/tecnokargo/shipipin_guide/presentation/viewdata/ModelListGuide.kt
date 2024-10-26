package com.delivery.tecnokargo.shipipin_guide.presentation.viewdata

import java.util.UUID.randomUUID

data class Product(
    val id: String,
    val nombre: String,
    var checking: Boolean,
    val descripcion: String,
    val travelRouteId: String,
    val trakingcode: String = randomUUID().toString().uppercase().replace("-", "").take(10)
)

data class TravelRoute(
    val id: String,
    val direccion: String,
    val guideRouteId: String,
    val checking: Boolean,
    val statusdelivery: Int = 0,
    val delivered: String = "",
    val personname: String = "own name",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
)

data class GuideRoute(
    val id: String,
    val description: String
)


