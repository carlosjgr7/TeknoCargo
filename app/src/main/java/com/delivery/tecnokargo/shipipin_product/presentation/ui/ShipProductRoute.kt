package com.delivery.tecnokargo.shipipin_product.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.TopBar

@Composable
fun ShippingProductRute(
    id: String,
    gotoBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopBar("Products Route List") {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clickable { gotoBack.invoke() },
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        content = { paddingValues ->
            ShippingProductRouteContent(
                paddingValues,
                selected = {

                },
                ruteId = id

            )
        }
    )

}

@Composable
fun ShippingProductRouteContent(
    paddingValues: PaddingValues,
    selected: () -> Unit,
    ruteId: String
    ) {



}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingProductRutePreview() {
    ShippingProductRute("TR001")
}