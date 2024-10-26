package com.delivery.tecnokargo.main.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.tecnokargo.components.PieChart


@Composable
fun BigSection(
    visible: Boolean,
    speedAnimation: Int,
    modifier: Modifier,
    goToStatistics: () -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        StatisticsSection(visible, speedAnimation, modifier = Modifier.weight(1f)) {
            goToStatistics.invoke()
        }

    }
}


@Composable
private fun StatisticsSection(
    visible: Boolean,
    speedAnimation: Int,
    modifier: Modifier,
    colorbackGround: Color = MaterialTheme.colorScheme.onTertiary,
    goToStatistics: () -> Unit,
) {
    Box(modifier = modifier) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = speedAnimation)) +
                    slideInVertically(
                        initialOffsetY = { it + 100 },
                        animationSpec = tween(durationMillis = speedAnimation)
                    )
        ) {

            Box(
                modifier = Modifier
                    .background(colorbackGround, RoundedCornerShape(12.dp))
                    .clickable { goToStatistics.invoke() }
            ) {
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        PieChart()
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Statistics",
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    }


                }
            }


        }
    }
}

@Preview
@Composable
fun BigSectionPreview() {
    BigSection(true, 350, Modifier.fillMaxSize(), {})
}

