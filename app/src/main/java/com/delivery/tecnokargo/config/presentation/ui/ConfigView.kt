package com.delivery.tecnokargo.config.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.BottomNavigationBar
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.core.navigations.ButtomBarOptions
import com.delivery.tecnokargo.main.presentation.ui.MainScreenContent

@Composable
fun ConfigView(
    goToHome: () -> Unit = {},
    logout: () -> Unit = {},

    ) {
    Scaffold(
        topBar = { TopBar("Settings") },
        bottomBar = {
            BottomNavigationBar(ButtomBarOptions.Settings) {
                when (it) {
                    ButtomBarOptions.Home -> {
                        goToHome.invoke()
                    }
                    ButtomBarOptions.Settings -> {}
                }
            }
        },
        content = { paddingValues ->
            ConfigScreenContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.background),

                ){
                logout.invoke()
            }
        }
    )
}

@Composable
fun ConfigScreenContent(modifier: Modifier, logout: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier =
            Modifier
                .size(80.dp)
                .background(MaterialTheme.colorScheme.onTertiary, CircleShape)
                .clip(CircleShape)
                .padding(16.dp)
            ,
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_person_24),
                contentDescription = "Profile Image",
                modifier = Modifier.fillMaxSize()
            )

        }

        Spacer(
            modifier =
            Modifier
                .padding(top = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.outlineVariant),
        )

        ProfileActionIcon(
            icon = R.drawable.baseline_logout_24,
            title = "Logout",
            onClick = {
                logout.invoke()
            }
        )


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfigViewPreview() {
    ConfigView()
}
