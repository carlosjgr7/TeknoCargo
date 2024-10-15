package com.delivery.tecnokargo.shipipin_guide.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.core.navigations.Routes
import com.delivery.tecnokargo.mockdata.mockGuideRoutes
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.GuideRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShipinGuideScreen(
    advance: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar("Shipping Guide List") {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { advance(Routes.Back.route) },
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = { paddingValues ->
            ShippingGuideContent(
                paddingValues,
                selected = {
                    advance(it)
                }

            )
        },

        )

}

@Composable
fun ShippingGuideContent(
    paddingValues: PaddingValues,
    selected: (String) -> Unit = {}
) {
    val guideRoutes = mockGuideRoutes()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize(),
        contentPadding = paddingValues
    ) {
        items(guideRoutes.size) { cont ->
            CardContent(
                guideRoutes[cont],
                selected = {
                    selected(it.id)
                }
            )
        }
    }

}

@Composable
fun CardContent(
    guideRoute: GuideRoute,
    modifier: Modifier = Modifier,
    selected: (GuideRoute) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(14.dp)
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            )
            .clickable {
                selected(guideRoute)
            },

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_format_list_bulleted_24),
                contentDescription = "icon list",
                colorFilter = ColorFilter.tint(Color.Blue)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = guideRoute.description)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShipinGuideScreenPreview() {
    ShipinGuideScreen()
}