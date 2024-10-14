package com.delivery.tecnokargo.shipipin_guide.presentation.viewdata

data class Product(
    val codigo: String,
    val nombre: String,
    val descripcion: String
)

data class TravelRoute(
    val codigo: String,
    val direccion: String,
    val list_products: List<String>
)

data class GuideRoute(
    val codigo: String,
    val list_travelRoutes: List<String>,
    val description: String
)
