package com.delivery.tecnokargo.login.data.datasource.network

import android.util.Log
import com.delivery.tecnokargo.core.SP
import com.delivery.tecnokargo.login.data.datasource.network.request.LoginRequest
import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRemoteDataSource @Inject constructor(
    private val apiLoginService: ILoginService
) {
    suspend fun login(data: List<String>): Flow<Result<LoginResponse>> = flow {
        try {
            val response = apiLoginService.login(LoginRequest(SP.SP_Validar_Usuario.name, data))
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
            Log.i(SP.SP_Validar_Usuario.name, "${SP.SP_Validar_Usuario.name} error: ${e.message}")
        }
    }
}