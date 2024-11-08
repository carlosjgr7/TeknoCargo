package com.delivery.tecnokargo.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GenericAlertDialog(
    onDismiss: () -> Unit,
    title: String,
    message: String,
    confirmButton: String,
    onAction: () -> Unit,
    showCancel: Boolean = true,

) {
    AlertDialog(
        onDismissRequest ={},
        title = {
            Text(
                text = title
            )
        },
        text = {
            Text(
                text = message
            )
        },
        confirmButton = {
            Button(onClick = {onAction.invoke()}) {
                Text(
                    text = confirmButton
                )
            }
        },

        dismissButton = {
            if (showCancel)
            Button(onClick = { onDismiss.invoke() }) {
                Text(
                    text = "Cancel"
                )
            }
        }
    )

}