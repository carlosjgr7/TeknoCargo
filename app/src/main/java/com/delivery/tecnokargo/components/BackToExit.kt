package com.delivery.tecnokargo.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@Composable
fun BackToExitHandler(state: Boolean? = null, onClose: (() -> Unit)? = null) {
    val context = LocalContext.current
    var doubleBackToExitPressedOnce = remember { mutableStateOf(false) }

    BackHandler(onBack = {
            exitProcess(0)
    })
}