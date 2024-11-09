package com.delivery.tecnokargo.login.domain

import com.delivery.tecnokargo.login.data.repository.ILoginRepository
import com.delivery.tecnokargo.login.presentation.model.LoginPresentationData
import com.delivery.tecnokargo.login.presentation.model.toPresentation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: ILoginRepository
) {
    suspend operator fun invoke(data: List<String>): Flow<Result<LoginPresentationData>> =
        loginRepository.login(data).map { result ->
            result.fold(
                onSuccess = { succesResponse ->
                    Result.success(succesResponse.toPresentation())
                },
                onFailure = {
                    Result.failure(it)
                }
            )

        }
}


