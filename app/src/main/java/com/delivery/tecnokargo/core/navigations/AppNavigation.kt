package com.delivery.tecnokargo.core.navigations

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.delivery.tecnokargo.login.presentation.ui.LoginScreen
import com.delivery.tecnokargo.main.presentation.ui.MainScreen
import com.delivery.tecnokargo.shipipin_guide.presentation.ui.ShipGuideScreen
import com.delivery.tecnokargo.shipipin_product.presentation.ui.ShippingProductRute
import com.delivery.tecnokargo.shipipin_rute.presentation.ui.ShippingGuideRute
import com.delivery.tecnokargo.splash.presentation.ui.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val animationSpeed = 350
    val startDestination = Splash

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Splash> {
            SplashScreen { navController.navigate(Login) }
        }

        composable<Login>(
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
                navController.navigate(Home)
            }
        }

        composable<Home>(
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
            MainScreen(
                goToShippinGuide = { navController.navigate(ShippingGuide) },
                goToStatistics = { navController.navigate(Statistics) },
                goToMoveShipping = { navController.navigate(MoveShipping) },
                goToBack = { navController.popBackStack() }
            )
        }

        composable<ShippingGuide>(
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
            ShipGuideScreen(
                goToShippingGuideRutes = { id ->
                    navController.navigate(ShippingGuideRoute(id))
                },
                goToBack = { navController.popBackStack() }
            )
        }

        composable<ShippingGuideRoute>(
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
            val shippingGuideRoute: ShippingGuideRoute = backStackEntry.toRoute()
            ShippingGuideRute(
                id = shippingGuideRoute.id,
                goToRuteDetail = {
                    navController.navigate(ShippingProductRoute(it))
                },
                gotoBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<ShippingProductRoute>(
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
            val routeID: ShippingProductRoute = backStackEntry.toRoute()
            ShippingProductRute(
                id = routeID.id,
                gotoBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}