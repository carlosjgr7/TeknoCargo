package com.delivery.tecnokargo.login.data.repository

import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse
import kotlinx.coroutines.flow.Flow

interface ILoginRepository{
    suspend fun login(data : List<String>): Flow<Result<LoginResponse>>

}