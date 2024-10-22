package com.delivery.tecnokargo.shipipin_guide.presentation.viewdata

import java.util.UUID.randomUUID

data class Product(
    val id: String,
    val nombre: String,
    var checking: Boolean,
    val descripcion: String,
    val travelRouteId: String,
    val code: String = randomUUID().toString().uppercase().replace("-", "").take(10)
)

data class TravelRoute(
    val id: String,
    val direccion: String,
    val guideRouteId: String,
    var checking: Boolean
)

data class GuideRoute(
    val id: String,
    val description: String
)
