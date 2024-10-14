package com.delivery.tecnokargo.main.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.delivery.tecnokargo.R
import com.delivery.tecnokargo.components.BottomNavigationBar
import com.delivery.tecnokargo.components.TopBar
import com.delivery.tecnokargo.core.navigations.Routes
import com.delivery.tecnokargo.main.presentation.components.CategorySelected

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    selectOption: (String) -> Unit,
) {
    Scaffold(
        topBar = { TopBar("Main Screen") },
        bottomBar = {
            BottomNavigationBar(Routes.Home) {
                if (it !== Routes.Home.route) selectOption.invoke(it)
            }
        },
        content = { paddingValues ->
            MainScreenContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(MaterialTheme.colorScheme.onSecondary),
                selectOption = {
                    selectOption.invoke(it)
                }
            )
        }
    )
}


@Composable
fun MainScreenContent(
    selectOption: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxSize(),

    ) {

        val shippinGuideAnimation = R.raw.guiderute
        val BuildAnimation = R.raw.build
        val speed = 1200

        val (shippinGuide, BuildingCategory, greeting, BuildingCategory2, BuildingCategory3) = createRefs()
        val startGuideline = createGuidelineFromStart(12.dp)
        val toptGuideline = createGuidelineFromTop(12.dp)
        val bottomGuideline = createGuidelineFromBottom(25.dp)

        var visible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            visible = true
        }

        createHorizontalChain(
            shippinGuide,
            BuildingCategory,
            chainStyle = ChainStyle.Spread
        )

        Box(
            modifier = Modifier
                .constrainAs(greeting) {
                    start.linkTo(startGuideline)
                    top.linkTo(toptGuideline)
                }
                .padding(bottom = 25.dp)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = speed)) +
                        slideInVertically(
                            initialOffsetY = { -it },
                            animationSpec = tween(durationMillis = speed)
                        )
            ) {
                Text(
                    text = "Hello, Carlos Javier",
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    fontSize = 24.sp,

                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(shippinGuide) {
                    start.linkTo(startGuideline)
                    top.linkTo(greeting.bottom)
                }
                .fillMaxWidth(0.45f)
                .fillMaxHeight(0.5f)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = speed)) +
                        slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(durationMillis = speed))
            ) {
                CategorySelected(
                    color = MaterialTheme.colorScheme.primary,
                    animation = shippinGuideAnimation,
                    categoryname = "Shipping Guide",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }


        Box(
            modifier = Modifier
                .constrainAs(BuildingCategory) {
                    start.linkTo(shippinGuide.end)
                    top.linkTo(shippinGuide.top)
                }
                .fillMaxHeight(0.34f)
                .fillMaxWidth(0.45f)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = speed)) +
                        slideInVertically(
                            initialOffsetY = { -it },
                            animationSpec = tween(durationMillis = speed)
                        )
            ) {
                CategorySelected(
                    color = Color(0xfff9f9c5),
                    animation = BuildAnimation,
                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(BuildingCategory2) {
                    start.linkTo(shippinGuide.start)
                    bottom.linkTo(bottomGuideline)

                }
                .fillMaxWidth(0.45f)
                .fillMaxHeight(0.34f)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = speed)) +
                        slideInVertically(
                            initialOffsetY = { it+100 },
                            animationSpec = tween(durationMillis = speed)
                        )
            ) {
                CategorySelected(
                    color = Color(0xff759df0),
                    animation = BuildAnimation,

                )
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(BuildingCategory3) {
                    start.linkTo(BuildingCategory.start)
                    end.linkTo(BuildingCategory.end)
                    bottom.linkTo(bottomGuideline)
                }
                .fillMaxWidth(0.45f)
                .fillMaxHeight(0.5f)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(animationSpec = tween(durationMillis = speed)) +
                        slideInHorizontally(initialOffsetX = { it+100 }, animationSpec = tween(durationMillis = speed))

            ) {

                CategorySelected(
                    color = Color(0xff933927),
                    animation = BuildAnimation,

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(selectOption = {})
}