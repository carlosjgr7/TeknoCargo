package com.delivery.tecnokargo.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.core.navigations.ButtomBarOptions

@Composable
fun BottomNavigationBar(
    activeRote: ButtomBarOptions,
    navigateTo : (ButtomBarOptions) -> Unit
) {
    val bottomNavigationItems = listOf(
        NavigationItem(ButtomBarOptions.Home, "Home", Icons.Filled.Home),
        NavigationItem(ButtomBarOptions.Settings, "Settings", Icons.Filled.Settings)
    )

    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        actions = {
            bottomNavigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    modifier = Modifier.weight(if(item.label!="Settings") 1f else 0.5f),
                    icon = { Icon(item.icon, contentDescription = "item.label") },
                    label = { Text(item.label) },
                    selected = activeRote == item.option,
                    onClick = {
                        navigateTo.invoke(item.option)
                    }
                )
                if (index < bottomNavigationItems.size - 1) {
                    VerticalDivider(
                        modifier = Modifier.padding(vertical = 16.dp),
                        thickness = 2.dp
                    )
                }
            }
        },
    )
}

data class NavigationItem(val option: ButtomBarOptions, val label: String, val icon: ImageVector)
