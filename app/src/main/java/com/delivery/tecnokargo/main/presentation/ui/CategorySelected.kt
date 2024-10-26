package com.delivery.tecnokargo.main.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    colorbackGround: Color = MaterialTheme.colorScheme.onTertiary,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))

    Box(
        modifier = modifier
            .background(colorbackGround, RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = categoryname,
                modifier = Modifier,
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            LottieAnimation(
                composition = composition,
                iterations = if (categoryname == "Building") LottieConstants.IterateForever else 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                alignment = Alignment.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CategorySelectedPreview() {
    CategorySelected(animation = R.raw.build)
}