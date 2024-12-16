package com.delivery.tecnokargo.core.navigations.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable


sealed class BottomBarItem {
    abstract val route: BottomBarDestination
    abstract val title: String
    abstract val icon: @Composable () -> Unit


    data class Home(
        override val route: BottomBarDestination = BottomBarDestination.Home,
        override val title: String = "Home",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
        }
    ) : BottomBarItem()

    data class Settings(
        override val route: BottomBarDestination = BottomBarDestination.Settings,
        override val title: String = "Settings",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
        }
    ) : BottomBarItem()
}

enum class BottomBarDestination {
    Home, Settings
}