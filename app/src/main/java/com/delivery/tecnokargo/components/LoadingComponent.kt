package com.delivery.tecnokargo.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingComponent() {
    Dialog(onDismissRequest = {}) {
        CircularProgressIndicator()
    }
}