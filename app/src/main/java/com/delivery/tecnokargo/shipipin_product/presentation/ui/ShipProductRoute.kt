package com.delivery.tecnokargo.shipipin_product.presentation.ui

import android.content.Context
import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.RecognizerBarCode
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.shipipin_guide.presentation.viewdata.Product
import com.delivery.tecnokargo.shipipin_product.presentation.viewmodels.ShippingProductViewModel
import com.delivery.tecnokargo.shipipin_rute.presentation.model.RequestProductsEnum

@Composable
fun ShippingProductRute(
    id: String,
    seekProducts: RequestProductsEnum = RequestProductsEnum.TRAVEL_ROUTE,
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
                ruteId = id,
                type = seekProducts
            )
        }
    )

}


@Composable
fun ShippingProductRouteContent(
    paddingValues: PaddingValues,
    ruteId: String,
    type: RequestProductsEnum
) {
    val viewModel = hiltViewModel<ShippingProductViewModel>()
    viewModel.getProducts(ruteId, type)

    var productsviemodel = viewModel.productsFilter.collectAsState()

    var products = remember { mutableStateOf(productsviemodel.value) }
    val context = LocalContext.current
    var productid by remember { mutableStateOf("") }
    var aling by remember { mutableStateOf(Alignment.CenterHorizontally) }


    var viewScanner by remember { mutableStateOf(false) }
    var dynamicWeight by remember { mutableFloatStateOf(1f) }
    val animatedWeight by animateFloatAsState(
        targetValue = dynamicWeight,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Column(
        Modifier
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.onSecondary)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .weight(animatedWeight)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {
            AnimatedVisibility(
                visible = viewScanner,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Box(
                    Modifier
                        .height(80.dp)
                        .width(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFCDCDCD))
                ) {
                    RecognizerBarCode(
                        onDimissPermission = { viewScanner = false },
                        onScan = {
                            productid = it.uppercase()
                            products.value = toggleProductChecking(products.value, productid, context) {}
                        }
                    )
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                OutlinedTextField(
                    value = productid,
                    onValueChange = { productid = it.uppercase() },
                    label = { Text("Product ID") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.size(32.dp),
                            onClick = {
                                viewScanner = !viewScanner
                                dynamicWeight = if (viewScanner) 1.5f else 1f
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
                    products.value = toggleProductChecking(products.value, productid, context) {
                        productid = ""
                    }
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
                items(products.value.size) { cont ->
                    CardProductContent(
                        products.value[cont],
                        selected = {
                            productid = it
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun CardProductContent(product: Product, selected: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { selected(product.trakingcode) }) {
        Image(
            painter = painterResource(if (product.checking) R.drawable.producto else R.drawable.caja),
            contentDescription = "back",
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.CenterVertically)
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 5.dp)
        ) {
            Text(product.nombre)
            Text(product.descripcion)
            Text(product.trakingcode)
        }

    }
    HorizontalDivider(color = MaterialTheme.colorScheme.primary)
}

private fun toggleProductChecking(
    products: List<Product>,
    productId: String,
    context: Context,
    checked: () -> Unit = {},
): List<Product> {
    val audioResourceId = R.raw.pitido
    val mediaPlayer = MediaPlayer.create(context, audioResourceId)
    val vibrator = context.getSystemService(Vibrator::class.java)
    return products.map { product ->
        if (product.trakingcode.uppercase() == productId && !product.checking) {
            mediaPlayer.start()
            vibrator?.vibrate(
                VibrationEffect
                    .createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
            )
            checked.invoke()
            product.copy(checking = true)
        } else {
            product
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingProductRutePreview() {
    ShippingProductRute("TR001")
}