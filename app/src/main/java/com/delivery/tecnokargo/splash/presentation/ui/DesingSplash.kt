package com.delivery.tecnokargo.splash.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.delivery.tecnokargo.R

@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    scaleAnimation: Animatable<Float, AnimationVector1D>,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.box4))
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            composition = composition,
            iterations = 1,
            speed = 4f,
            modifier = Modifier
                .fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit
        )

    }

}