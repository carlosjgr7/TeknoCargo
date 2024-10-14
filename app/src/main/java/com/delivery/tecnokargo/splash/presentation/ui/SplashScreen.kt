package com.delivery.tecnokargo.splash.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.delivery.tecnokargo.R

@Composable
fun SplashScreen(
    goToMain: () -> Unit,
) {
    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }


    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        durationMillisAnimation = 1000,
        delayScreen = 100L,
        goToMain = {
            goToMain.invoke()
        }
    )

    DesignSplashScreen(
        imagePainter = painterResource(
            id = R.drawable.ic_launcher_foreground,
        ),
        scaleAnimation = scaleAnimation,
    )
}

