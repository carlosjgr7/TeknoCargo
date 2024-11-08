package com.delivery.tecnokargo.move_shipping.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.TopBar


@Composable
fun MoveRouteView(
    gotoBack: () -> Unit = {},
){
    Scaffold(
        topBar = {
            TopBar("Move Shipping Rute") {
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
            MoveRouteContent(
                modifier = Modifier
                    .padding(paddingValues)
            )
        }
    )


}

@Composable
fun MoveRouteContent(modifier: Modifier) {
    Column (
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var ruteId by remember { mutableStateOf("") }


        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { },
                painter = painterResource(R.drawable.baseline_info_outline_24),
                contentDescription = "Información",
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "indica el id de la persona a la que vas a auxiliar",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        OutlinedTextField(
            value = ruteId,
            onValueChange = { ruteId = it },
            label = { Text("ruteId") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 16.dp) .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF00BCD4),
                        Color(0xFF0097A7)
                    )
                ),
                shape = RoundedCornerShape(8.dp) // Rounded corners
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,

            )
        ) {

                Text(text = "Move Rute", color = Color.White) // Set text color to white

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MoveRouteViewPreview() {
    MoveRouteView(
        gotoBack = {}
    )
}