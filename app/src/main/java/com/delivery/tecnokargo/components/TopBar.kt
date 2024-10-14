package com.delivery.tecnokargo.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.onSecondary,
        titleContentColor = MaterialTheme.colorScheme.primary,
        navigationIconContentColor = Color.White,
        actionIconContentColor = Color.White
    )

    TopAppBar(
        title = { Text(title) },
        colors = topAppBarColors
    )


}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("Main Screen")
}