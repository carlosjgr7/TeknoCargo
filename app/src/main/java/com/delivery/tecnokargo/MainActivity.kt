package com.delivery.tecnokargo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.delivery.tecnokargo.core.navigations.AppNavigation
import com.delivery.tecnokargo.ui.theme.TecnokargoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TecnokargoTheme {
                AppNavigation()
            }
        }
    }
}

