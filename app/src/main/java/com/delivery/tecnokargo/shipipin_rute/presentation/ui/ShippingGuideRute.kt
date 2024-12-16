package com.delivery.tecnokargo.shipipin_rute.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.GenericAlertDialog
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.mockdata.mockTravelRoutes
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.TravelRoute
import com.delivery.tecnokargo.shipipin_rute.presentation.model.RequestProductsEnum

@Composable
fun ShippingGuideRute(
    id: String,
    goToRuteDetail: (String, RequestProductsEnum) -> Unit = ({ _, _ -> }),
    goToMap: () -> Unit = {},
    gotoBack: () -> Unit = {},

    ) {

    Scaffold(
        topBar = {
            TopBar("Shipping Guide List Rute") {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable {
                            gotoBack.invoke()
                        },
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = { paddingValues ->
            ShippingGuideRuteContent(
                paddingValues,
                selected = { id, type ->
                    goToRuteDetail(id, type)
                },
                goToMap = {
                    goToMap.invoke()
                },
                guideId = id,
            )
        },

        )

}

@Composable
fun ShippingGuideRuteContent(
    paddingValues: PaddingValues,
    selected: (String, RequestProductsEnum) -> Unit,
    guideId: String,
    goToMap: () -> Unit
) {
    val travelRoute = mockTravelRoutes().filter { it.guideRouteId == guideId }
    val readyToStart = travelRoute.firstOrNull { !it.checking } == null
    val checkin = remember { mutableStateOf(false) }

    if (checkin.value) {
        GenericAlertDialog(
            title = "RUTAS NO REVISADAS",
            message = "Los viajes solo pueden iniciar si reviso todos los productos de cada ruta",
            confirmButton = "OK",
            onAction = {
                checkin.value = false
            },
            onDismiss = {},
            showCancel = false
        )
    }

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
                        selected(it.id, RequestProductsEnum.TRAVEL_ROUTE)
                    }
                )
            }
        }


        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(horizontal = 14.dp, vertical = 20.dp),
            onClick = {
                if (readyToStart) goToMap.invoke() else checkin.value = true
            },
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
            expanded = readyToStart
        )

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 14.dp, vertical = 20.dp),
            onClick = {
                selected(guideId, RequestProductsEnum.GUIDE_ROUTE)
            },
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            Image(
                painter = painterResource(id = R.drawable.all_products),
                contentDescription = "Extended floating action button.",
                modifier = Modifier.size(24.dp),
            )
        }


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
            .background(if (travelRoute.checking) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error)
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