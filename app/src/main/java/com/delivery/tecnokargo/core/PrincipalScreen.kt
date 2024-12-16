package com.delivery.tecnokargo.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delivery.tecnokargo.core.navigations.Home
import com.delivery.tecnokargo.core.navigations.Settings
import com.delivery.tecnokargo.core.navigations.bottomnavigation.BottomBarDestination
import com.delivery.tecnokargo.core.navigations.bottomnavigation.BottomBarItem
import com.delivery.tecnokargo.core.navigations.bottomnavigation.NavigationBottomWrapper

@Composable
fun PrincipalScreen(
    goToShippinGuide: () -> Unit,
    goToStatistics: () -> Unit,
    goToMoveShipping: () -> Unit,
    logout : () -> Unit
) {
    val items = listOf(BottomBarItem.Home(), BottomBarItem.Settings())
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigation(items, navController) }) { innerPadding ->
        Box {
            NavigationBottomWrapper(
                navController, innerPadding,
                goToShippinGuide = { goToShippinGuide.invoke() },
                goToStatistics = { goToStatistics.invoke() },
                goToMoveShipping = { goToMoveShipping.invoke() },
                logout = { logout.invoke() }
            )
        }

    }
}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route?.contains(item.route.name) == true
                } == true,
                onClick = {
                    val destination = when (item.route) {
                        BottomBarDestination.Home -> Home
                        BottomBarDestination.Settings -> Settings
                        else -> Home
                    }
                    navController.navigate(destination) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                label = { Text(item.title) }
            )
        }
    }
}