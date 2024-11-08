package com.delivery.tecnokargo.statistics.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.components.DetailsPieChartItem
import com.delivery.tecnokargo.statistics.data.model.ChartPieModel

@Composable
fun ShowItemsPieChart(data: List<ChartPieModel>, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        data.forEach {
            DetailsPieChartItem(
                it,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
            )
        }
    }

}