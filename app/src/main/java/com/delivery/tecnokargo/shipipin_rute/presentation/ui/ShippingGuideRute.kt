package com.delivery.tecnokargo.shipipin_rute.presentation.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.core.navigations.Routes
import com.delivery.tecnokargo.mockdata.mockTravelRoutes
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.TravelRoute

@Composable
fun ShippingGuideRute(id: String, advance: (String) -> Unit = {}) {

    Scaffold(
        topBar = {
            TopBar("Shipping Guide List Rute") {
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
            ShippingGuideRuteContent(
                paddingValues,
                selected = {
                    advance(it)
                },
                guideId = id,
            )
        },

        )

}

@Composable
fun ShippingGuideRuteContent(
    paddingValues: PaddingValues,
    selected: (String) -> Unit,
    guideId: String
) {
    val travelRoute = mockTravelRoutes().filter { it.guideRouteId == guideId }
    var expandRoute = travelRoute.firstOrNull { !it.checking } == null

    Box {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onSecondary)
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {
            items(travelRoute.size) { cont ->
                CardContent(
                    travelRoute[cont],
                    selected = {
                        selected(it.id)
                    }
                )
            }
        }

        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 14.dp, vertical = 20.dp),
            onClick = { !expandRoute },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.start_truck),
                    contentDescription = "Extended floating action button.",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primaryContainer

                )
            },
            text = {
                Text(
                    text = "Start Travel",
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            },
            containerColor = MaterialTheme.colorScheme.primary,
            expanded = expandRoute
        )

    }


}

@Composable
fun CardContent(
    travelRoute: TravelRoute,
    modifier: Modifier = Modifier,
    selected: (TravelRoute) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(14.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.secondary)
            .clickable {
                selected(travelRoute)
            },

        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = CenterVertically,
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(R.drawable.map),
                contentDescription = "icon list",
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = travelRoute.direccion)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingGuideRutePreview() {
    ShippingGuideRute("G001")
}