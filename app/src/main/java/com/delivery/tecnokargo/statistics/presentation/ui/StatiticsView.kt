package com.delivery.tecnokargo.statistics.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.PieChart
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.statistics.data.model.ChartPieModel

@Composable
fun StatisticsView(
    goToBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar("Statistics") {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { goToBack.invoke() },
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = { paddingValues ->
            StatisticsContent(
                modifier = Modifier
                    .padding(paddingValues)
            )
        }
    )
}

@Composable
fun StatisticsContent(modifier: Modifier) {
    //Data mock que me dio flojera crear donde deveria crearla XD
    val dataPie = listOf(
        ChartPieModel("Entregados", 70, Color(0xFFFFC107)),
        ChartPieModel("En espera", 30, Color(0xFF03A9F4)),
        ChartPieModel("Fallidos", 45, Color(0xFFC95B38)),
        ChartPieModel("Devueltos", 25, Color(0xFFCE93D8)),
        ChartPieModel("En tránsito", 15, Color(0xFF76B25E))
    )
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { },
                painter = painterResource(R.drawable.baseline_info_outline_24),
                contentDescription = "Información",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "¿Cómo van tus envíos? Da un vistazo al estado de cada entrega.",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.size(8.dp))

        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.onTertiary,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(
                modifier = Modifier .padding(start = 16.dp, end = 16.dp, bottom = 6.dp, top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Estado de Envíos",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 2.dp)
                )

                Row(
                    Modifier
                        .padding(4.dp)
                ) {
                    PieChart(
                        data = dataPie,
                        modifier = Modifier
                            .weight(1.2f)
                            .size(250.dp)
                            .padding(6.dp),
                        animationChart = true
                    )

                    ShowItemsPieChart(
                        data = dataPie,
                        modifier = Modifier
                            .size(250.dp)
                            .weight(1f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(8.dp))

        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .background(
                    MaterialTheme.colorScheme.onTertiary,
                    RoundedCornerShape(12.dp)
                ),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 10.dp)
                    .background(MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(16.dp))
                    .padding(16.dp),
            ) {
                Text(
                    text = "Leyenda",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(8.dp))

                val legendItems = listOf(
                    Pair("Entregados", "Envíos finalizados con éxito." to Color(0xFFFFC107)),
                    Pair("En espera", "Envíos pendientes de entrega." to Color(0xFF03A9F4)),
                    Pair("Fallidos", "Envíos que no pudieron ser entregados." to Color(0xFFC95B38)),
                    Pair("Devueltos", "Envíos retornados al origen." to Color(0xFFCE93D8)),
                    Pair("En tránsito", "Envíos en movimiento hacia su destino." to Color(0xFF76B25E))
                )

                legendItems.forEach { (title, description) ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(description.second, RoundedCornerShape(4.dp))
                        )
                        Spacer(Modifier.size(8.dp))
                        Column {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = description.first,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StatisticsViewPreview() {
    StatisticsView(
        goToBack = {}
    )
}
