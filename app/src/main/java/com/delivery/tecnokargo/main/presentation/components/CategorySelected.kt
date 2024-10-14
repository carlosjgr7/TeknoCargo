package com.delivery.tecnokargo.main.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.delivery.tecnokargo.R

@Composable
fun CategorySelected(
    modifier: Modifier = Modifier,
    animation: Int,
    categoryname: String = "Building",
    color: Color = Color.White
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color, RoundedCornerShape(12.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = categoryname,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 40.dp),
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            LottieAnimation(
                composition = composition,
                iterations = if (categoryname == "Building") LottieConstants.IterateForever else 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                alignment = Alignment.TopCenter
            )


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategorySelectedPreview() {
    CategorySelected(animation = R.raw.build)
}