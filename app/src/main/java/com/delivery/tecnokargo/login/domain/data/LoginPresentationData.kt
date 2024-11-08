package com.delivery.tecnokargo.login.domain.data

import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse

data class LoginPresentationData(
    val name: String,
)

fun LoginResponse.toPresentation(): LoginPresentationData {
    return LoginPresentationData(
        name = this.message,
    )
}