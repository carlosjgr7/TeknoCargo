package com.delivery.tecnokargo.main.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.components.BottomNavigationBar
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.core.navigations.ButtomBarOptions

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    goToShippinGuide: () -> Unit,
    goToStatistics: () -> Unit,
    goToMoveShipping: () -> Unit,
    goToBack: () -> Unit,
) {
    Scaffold(
        topBar = { TopBar("Main Screen") },
        bottomBar = {
            BottomNavigationBar(ButtomBarOptions.Home) {
                when (it) {
                    ButtomBarOptions.Home -> {}
                    ButtomBarOptions.Settings -> goToBack.invoke()
                    ButtomBarOptions.Profile -> {}
                }
                goToBack.invoke()
            }
        },
        content = { paddingValues ->
            MainScreenContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.onSecondary),
                goToShippinGuide = {
                    goToShippinGuide.invoke()
                },
                goToMoveShipping = {
                   // goToMoveShipping.invoke()
                },
                goToStatistics = {
                   // goToStatistics.invoke()
                }
            )
        }
    )
}


@Composable
fun MainScreenContent(
    goToShippinGuide: () -> Unit,
    goToMoveShipping: () -> Unit,
    goToStatistics: () -> Unit,
    modifier: Modifier = Modifier
) {
    val speedAnimation = 600
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxSize()
    ) {
        GreetingSection(visible, speedAnimation)
        SmallSection(visible, speedAnimation, Modifier.weight(1.4f),
            goToShippinGuide = {
                goToShippinGuide.invoke()
            },
            goToMoveShipping = {
                goToMoveShipping.invoke()
            }
        )
        Spacer(Modifier.padding(2.dp))
        BigSection(visible, speedAnimation, Modifier.weight(1f)) {
            goToStatistics.invoke()
        }

    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MainScreenPreview() {
    MainScreen(
        goToShippinGuide = {},
        {},
        {},
        {}
    )
}