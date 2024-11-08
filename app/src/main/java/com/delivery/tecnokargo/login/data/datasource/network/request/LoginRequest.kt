package com.delivery.tecnokargo.login.data.datasource.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("namesp")  val nameSP: String,
    @SerialName("parameters")  val params: List<String>,
)