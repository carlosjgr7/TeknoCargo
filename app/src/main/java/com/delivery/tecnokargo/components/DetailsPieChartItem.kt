package com.delivery.tecnokargo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.tecnokargo.statistics.data.model.ChartPieModel

@Composable
fun DetailsPieChartItem(
    data: ChartPieModel,
    height: Dp = 25.dp,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        color = Color.Transparent
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = data.color,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .align(Alignment.Top)
                    .size(height)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = data.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = data.cantidad.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    lineHeight = 16.sp
                )
            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsPieChartItemPreview() {
    DetailsPieChartItem(
        data = ChartPieModel("A", 30, Color(0xFF03A9F4))
    )
}