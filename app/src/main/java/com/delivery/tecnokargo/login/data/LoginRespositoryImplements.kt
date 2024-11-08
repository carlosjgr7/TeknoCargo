package com.delivery.tecnokargo.login.data

import com.delivery.tecnokargo.login.data.datasource.network.LoginRemoteDataSource
import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse
import com.delivery.tecnokargo.login.data.repository.ILoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImplements
@Inject constructor(
    private val repository: LoginRemoteDataSource
) : ILoginRepository {
    override suspend fun login(data: List<String>): Flow<Result<LoginResponse>> = repository.login(data)
}
