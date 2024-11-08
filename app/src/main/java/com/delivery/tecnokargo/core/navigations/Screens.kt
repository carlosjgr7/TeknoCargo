package com.delivery.tecnokargo.core.navigations

import com.delivery.tecnokargo.shipipin_rute.presentation.model.RequestProductsEnum
import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Login

@Serializable
object Home

@Serializable
object Settings

@Serializable
object ShippingGuide

@Serializable
object Statistics

@Serializable
object MoveShipping

@Serializable
data class ShippingGuideRoute(val id: String)

@Serializable
data class ShippingProductRoute(val id: String, val type: RequestProductsEnum)

