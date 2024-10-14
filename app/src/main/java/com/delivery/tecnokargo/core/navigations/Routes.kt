package com.delivery.tecnokargo.core.navigations

sealed class Routes(val route: String) {

    object Splash : Routes("splash")
    object Login : Routes("login")
    object Home : Routes("home")


}