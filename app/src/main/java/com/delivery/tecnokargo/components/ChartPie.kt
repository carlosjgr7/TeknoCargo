package com.delivery.tecnokargo.components

import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PieChart(
    data: List<Pair<String, Float>> =
        listOf(
            "A" to 30f,
            "B" to 70f,
            "C" to 45f,
            "D" to 25f,
        ),
    colors: List<Color>
    = listOf(
        Color(0xFF03A9F4),
        Color(0xFFFFC107),
        Color(0xFFC95B38),
        Color(0xFFCE93D8)
    ),
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val total = data.fold(0f) { acc, pair -> acc + pair.second }
    var startAngle = 0f

    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2
        val center = Offset(x = size.width / 2, y = size.height / 2)

        data.forEachIndexed { index, (label, value) ->
            val sweepAngle = (value / total) * 360f

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
                color = colors[index],
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
                label,
                labelOffset.x,
                labelOffset.y,
                TextPaint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 40f
                    typeface = android.graphics.Typeface.create(
                        android.graphics.Typeface.DEFAULT,
                        android.graphics.Typeface.BOLD
                    )
                }
            )

            if (index == data.lastIndex) {
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
            }

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

@Preview
@Composable
fun PieChartPreview() {
    val data = listOf(
        "A" to 30f,
        "B" to 70f,
        "C" to 45f,
        "D" to 25f,
    )
    val pastelColors = listOf(
        Color(0xFF03A9F4),
        Color(0xFFFFC107),
        Color(0xFFC95B38),
        Color(0xFFCE93D8)
    )
    PieChart(data = data, colors = pastelColors, modifier = Modifier
        .fillMaxSize()
        .padding(16.dp))
}