package com.delivery.tecnokargo.login.data.datasource.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginResponse(
    @SerialName("resultado_operacion") val message: String,
)