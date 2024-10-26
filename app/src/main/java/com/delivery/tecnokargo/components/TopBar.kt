package com.delivery.tecnokargo.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, icon: @Composable () -> Unit = {}) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.onTertiary,
        navigationIconContentColor = Color.White,
        actionIconContentColor = Color.White
    )

    TopAppBar(
        navigationIcon = {
            icon()
        },
        title = { Text(title) },
        colors = topAppBarColors
    )


}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("Main Screen")
}