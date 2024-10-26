package com.delivery.tecnokargo.core.navigations

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Login

@Serializable
object Home

@Serializable
object ShippingGuide

@Serializable
object Statistics

@Serializable
object MoveShipping

@Serializable
data class ShippingGuideRoute(val id: String)

@Serializable
data class ShippingProductRoute(val id: String)

