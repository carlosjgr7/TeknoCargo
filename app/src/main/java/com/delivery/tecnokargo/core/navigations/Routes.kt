package com.delivery.tecnokargo.core.navigations

sealed class Routes(val route: String) {

    data object Splash : Routes("splash")
    data object Login : Routes("login")
    data object Home : Routes("home")
    data object ShippingGuide : Routes("shippingguide")
    data object ShippingGuideRoute: Routes("shippingguiderute/{id}"){
        fun createRoute(id: String) = "shippingguiderute/$id"
    }
    data object Back: Routes("back")

}