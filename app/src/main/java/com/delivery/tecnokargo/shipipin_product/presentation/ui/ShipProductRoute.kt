package com.delivery.tecnokargo.shipipin_product.presentation.ui

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


    Column(
        Modifier
            .padding(paddingValues)
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
            OutlinedButton (
                onClick = {
                    aling =if(aling == Alignment.CenterHorizontally) Alignment.End else Alignment.CenterHorizontally
                },
                modifier = Modifier
                    .padding(horizontal = 12.dp).align(aling),
                shape = RoundedCornerShape(12.dp),


            ) {
                Text("Checking Product")
            }
        }

        Box(
            Modifier
                .weight(2f)
                .background(Color.Blue)
                .fillMaxSize()
        ) {
            Text("okok")
        }

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShippingProductRutePreview() {
    ShippingProductRute("TR001")
}