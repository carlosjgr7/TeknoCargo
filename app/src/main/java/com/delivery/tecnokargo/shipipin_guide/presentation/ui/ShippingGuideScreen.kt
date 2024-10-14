package com.delivery.tecnokargo.shipipin_guide.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delivery.tecnokargo.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShipinGuideScreen(){

    Scaffold (
        topBar = { TopBar("Shipping Guide List") },
        content = {
            ShippingGuideContent()
        }
    )

}

@Composable
fun ShippingGuideContent() {


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShipinGuideScreenPreview() {
    ShipinGuideScreen()
}