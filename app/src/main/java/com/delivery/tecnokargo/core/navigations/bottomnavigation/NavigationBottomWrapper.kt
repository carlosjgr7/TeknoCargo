package com.delivery.tecnokargo.core.navigations.bottomnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.delivery.tecnokargo.config.presentation.ui.ConfigView
import com.delivery.tecnokargo.core.navigations.Home
import com.delivery.tecnokargo.core.navigations.Settings
import com.delivery.tecnokargo.main.presentation.ui.MainScreen

@Composable
fun NavigationBottomWrapper(
    navController: NavHostController, innerPadding: PaddingValues,
    goToShippinGuide: () -> Unit,
    goToStatistics: () -> Unit,
    goToMoveShipping: () -> Unit,
    logout: () -> Unit
) {
    val startDestination = Home

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Home>() {
            MainScreen(
                goToShippinGuide = { goToShippinGuide.invoke() },
                goToStatistics = { goToStatistics.invoke() },
                goToMoveShipping = { goToMoveShipping.invoke() },
            )
        }
        composable<Settings>() {
            ConfigView(
                logout = {
                    logout.invoke()
                }
            )
        }
    }

}