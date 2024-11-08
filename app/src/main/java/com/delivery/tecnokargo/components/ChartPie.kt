package com.delivery.tecnokargo.components

import android.annotation.SuppressLint
import android.text.TextPaint
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.graphics.Paint
import com.delivery.tecnokargo.statistics.data.model.ChartPieModel

@SuppressLint("DefaultLocale")
@Composable
fun PieChart(
    animationChart: Boolean = true,
    data: List<ChartPieModel>,
    modifier: Modifier,
) {
    val total = data.fold(0f) { acc, pair -> acc + pair.cantidad }
    var startAngle = 0f

    var animationPlayed by remember { mutableStateOf(false) }
    val animDuration = 1000
    val percentageCalculate = data.sumOf { it.cantidad }

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed) {
            val rotations = (90f * 11f) / 360f
            val remainder = (90f * 11f) % 360f

            if (remainder == 0f) {
                90f * 11f
            } else {
                rotations.toInt() * 360f
            }
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = true) {
        if (animationChart) animationPlayed = true
    }



    Canvas(modifier = modifier.rotate(animateRotation)) {
        val radius = size.minDimension / 2
        val center = Offset(x = size.width / 2, y = size.height / 2)


        data.forEach { it ->
            val sweepAngle = (it.cantidad / total) * 360f
            val percentageString =
                String.format("%.2f", ((it.cantidad * 100).toFloat() / percentageCalculate))

            drawArc(
                color = Color(0xFFD3D3D3),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset(center.x - radius + 4, center.y - radius + 4),
                size = Size(radius * 2, radius * 2),
                style = Stroke(width = 8f)
            )

            drawArc(
                color = it.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2)
            )

            val labelAngle = startAngle + sweepAngle / 2
            val labelOffset = Offset(
                x = center.x + (radius / 1.5f) * kotlin.math.cos(Math.toRadians(labelAngle.toDouble()))
                    .toFloat(),
                y = center.y + (radius / 1.5f) * kotlin.math.sin(Math.toRadians(labelAngle.toDouble()))
                    .toFloat()
            )



            drawContext.canvas.nativeCanvas.drawText(
                "${percentageString}%",
                labelOffset.x,
                labelOffset.y,
                TextPaint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 30f
                    textAlign = Paint.Align.CENTER
                    typeface = android.graphics.Typeface.create(
                        android.graphics.Typeface.DEFAULT,
                        android.graphics.Typeface.BOLD
                    )
                }
            )

            val endAngle = startAngle + sweepAngle
            val lineEnd = Offset(
                x = center.x + radius * kotlin.math.cos(Math.toRadians(endAngle.toDouble()))
                    .toFloat(),
                y = center.y + radius * kotlin.math.sin(Math.toRadians(endAngle.toDouble()))
                    .toFloat()
            )
            drawLine(
                color = Color.Black,
                start = center,
                end = lineEnd,
                strokeWidth = 4f
            )


            startAngle += sweepAngle
        }

        drawCircle(
            color = Color.Black.copy(alpha = 0.2f),
            radius = radius + 4,
            center = center,
            style = Stroke(width = 8f)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PieChartPreview() {
    PieChart(
        data = listOf(
            ChartPieModel("A", 30, Color(0xFF03A9F4)),
            ChartPieModel("B", 70, Color(0xFFFFC107))
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}

