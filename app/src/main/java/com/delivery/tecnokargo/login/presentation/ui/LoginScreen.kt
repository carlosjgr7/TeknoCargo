package com.delivery.tecnokargo.login.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.delivery.tecnokargo.components.BackToExitHandler
import com.delivery.tecnokargo.components.GenericAlertDialog
import com.delivery.tecnokargo.components.LoadingComponent
import com.delivery.tecnokargo.core.Resources
import com.delivery.tecnokargo.login.presentation.model.LoginPresentationData
import com.delivery.tecnokargo.login.presentation.model.StateEnum
import com.delivery.tecnokargo.login.presentation.viewmodels.LoginViewModel


@Composable
fun LoginScreen(
    advance: () -> Unit,
) {
    BackToExitHandler()
    LoginContent(
        advance = {
            advance.invoke()
        },
    )
}

@Composable
fun LoginContent(
    advance: () -> Unit,
) {
    val viewModel: LoginViewModel = hiltViewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var stateShow by remember { mutableStateOf(StateEnum.NOTHING) }
    val loginState by viewModel.loginState.collectAsState()

    stateShow.let {
        when (it) {
            StateEnum.LOADING -> {
                LoadingComponent()
            }

            StateEnum.ERROR -> {
                GenericAlertDialog(
                    onDismiss = {},
                    title = "Login Error",
                    message = "Usuario o contraseña incorrectos",
                    confirmButton = "Aceptar",
                    showCancel = false,
                    onAction = {
                        stateShow = StateEnum.NOTHING
                    }
                )
            }

            StateEnum.NOTHING -> {}
        }
    }
    LaunchedEffect(loginState) {
        when (loginState) {
            is Resources.Loading -> {
                stateShow =
                    if ((loginState as Resources.Loading<LoginPresentationData>).data) StateEnum.LOADING else StateEnum.NOTHING
            }

            is Resources.Success -> {
                advance.invoke()
            }

            is Resources.Failure -> {
                stateShow = StateEnum.ERROR
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val image =
                    if (passwordVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        description,
                        tint = Color(0xFFCDCDCD),
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                viewModel.makeLogin(username, password)
            },
        ) {
            Text("Login", color = Color.White)
        }
    }


}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(advance = {})
}