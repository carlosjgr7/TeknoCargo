package com.delivery.tecnokargo.core.navigations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.delivery.tecnokargo.login.presentation.ui.LoginScreen
import com.delivery.tecnokargo.main.presentation.ui.MainScreen
import com.delivery.tecnokargo.shipipin_guide.presentation.ui.ShipinGuideScreen
import com.delivery.tecnokargo.shipipin_rute.presentation.ui.ShippingGuideRute
import com.delivery.tecnokargo.splash.presentation.ui.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val animationSpeed = 350
    val startDestination = Routes.Splash.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            route = Routes.Splash.route,
        ) {
            SplashScreen { navController.navigate(Routes.Login.route) }
        }

        composable(
            route = Routes.Login.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationSpeed),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
        ) {
            LoginScreen {
                navController.navigate(Routes.Home.route)
            }
        }

        composable(
            route = Routes.Home.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationSpeed),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
        ) {
            MainScreen {
                when (it) {
                    Routes.Home.route -> navController.navigate(Routes.Home.route)
                    Routes.Login.route -> navController.navigate(Routes.Login.route)
                    Routes.ShippingGuide.route -> navController.navigate(Routes.ShippingGuide.route)
                }
            }
        }

        composable(
            route = Routes.ShippingGuide.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationSpeed),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
        ) {
            ShipinGuideScreen() { action ->
                if (action == Routes.Back.route) navController.popBackStack()
                else navController.navigate(Routes.ShippingGuideRoute.createRoute(action)
                )
            }
        }


        composable(
            route = Routes.ShippingGuideRoute.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(animationSpeed),
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(animationSpeed),
                )
            },
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            ShippingGuideRute(id = id){action ->
                if (action == Routes.Back.route) navController.popBackStack()
                else navController.navigate(Routes.ShippingGuide.route)
            }
        }

    }
}