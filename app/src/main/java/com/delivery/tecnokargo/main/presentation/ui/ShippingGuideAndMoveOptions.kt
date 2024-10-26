package com.delivery.tecnokargo.main.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R

@Composable
fun SmallSection(
    visible: Boolean,
    speedAnimation: Int,
    modifier: Modifier,
    goToShippinGuide: () -> Unit,
    goToMoveShipping: () -> Unit
) {

    Row(modifier = modifier.fillMaxWidth()) {

        ShippingGuideBox(visible, speedAnimation, modifier = Modifier.weight(1f)) {
            goToShippinGuide.invoke()
        }
        Spacer(modifier = Modifier.width(6.dp))
        MoveShippingBox(visible, speedAnimation, modifier = Modifier.weight(1f)){
            goToMoveShipping.invoke()
        }
    }

}

@Composable
private fun ShippingGuideBox(
    visible: Boolean,
    speedAnimation: Int,
    modifier: Modifier = Modifier,
    goToShippinGuide: () -> Unit
) {
    Box(
        modifier = modifier
    ) {

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = speedAnimation)) +
                    slideInHorizontally(
                        initialOffsetX = { -it },
                        animationSpec = tween(durationMillis = speedAnimation)
                    )
        ) {
            Box(
                modifier = Modifier
                    .clickable { goToShippinGuide.invoke() }
            ) {
                CategorySelected(
                    animation = R.raw.guiderute,
                    categoryname = "Shipping Guide",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun MoveShippingBox(visible: Boolean, speedAnimation: Int, modifier: Modifier, goToMoveShipping: () -> Unit) {
    Box(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = speedAnimation)) +
                    slideInVertically(
                        initialOffsetY = { -it },
                        animationSpec = tween(durationMillis = speedAnimation)
                    )
        ) {
            Box(modifier = Modifier.clickable { goToMoveShipping.invoke() }) {
                CategorySelected(
                    animation = R.raw.build,
                    categoryname = "Move Shipping",
                )
            }
        }
    }
}