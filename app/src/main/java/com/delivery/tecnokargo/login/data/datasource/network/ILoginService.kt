package com.delivery.tecnokargo.login.data.datasource.network

import com.delivery.tecnokargo.login.data.datasource.network.request.LoginRequest
import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginService {
    @POST("/api/CallSP/call")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}