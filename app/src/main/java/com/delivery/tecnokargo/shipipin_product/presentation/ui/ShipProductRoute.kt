package com.delivery.tecnokargo.shipipin_product.presentation.ui

import android.graphics.drawable.PaintDrawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.mockdata.mockProducts
import com.delivery.tecnokargo.shipipin_guide.presentation.ui.CardContent
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.Product
import kotlinx.coroutines.delay

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
    var productid by remember { mutableStateOf("") }
    var aling by remember { mutableStateOf(Alignment.CenterHorizontally) }

    val products = mockProducts().filter { it.travelRouteId == ruteId }


    Column(
        Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .weight(1f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                OutlinedTextField(
                    value = productid,
                    onValueChange = { productid = it },
                    label = { Text("Product ID") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(32.dp),
                            onClick = {

                            }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.barcode),
                                contentDescription = "back",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                )

            }
            OutlinedButton(
                onClick = {
                     },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .align(aling),
                shape = RoundedCornerShape(12.dp),


                ) {
                Text("Checking Product")
            }
        }

        Box(
            Modifier
                .weight(2f)
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 38.dp, topEnd = 38.dp))
                .background(MaterialTheme.colorScheme.onTertiary)
                .padding(top = 38.dp, start = 24.dp, end = 24.dp, bottom = 0.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(products.size) { cont ->
                    CardProductContent(
                        products[cont],
                        selected = {
                            selected()
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun CardProductContent(product: Product, selected: () -> Unit) {
    Row(Modifier.fillMaxWidth()){
        Image(
            painter = painterResource(if(product.checking) R.drawable.producto else R.drawable.caja),
            contentDescription = "back",
            modifier = Modifier.size(42.dp).align(Alignment.CenterVertically)
        )
        Column(Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 5.dp)){
            Text(product.nombre)
            Text(product.descripcion)
            Text(product.code)
        }

    }
    HorizontalDivider( color = MaterialTheme.colorScheme.primary)

}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingProductRutePreview() {
    ShippingProductRute("TR001")
}