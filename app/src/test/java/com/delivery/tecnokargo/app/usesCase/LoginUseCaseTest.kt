package com.delivery.tecnokargo.app.usesCase

import com.delivery.tecnokargo.core.data.CONSTANTS
import com.delivery.tecnokargo.login.data.datasource.network.response.LoginResponse
import com.delivery.tecnokargo.login.data.repository.ILoginRepository
import com.delivery.tecnokargo.login.domain.LoginUseCase
import com.delivery.tecnokargo.login.presentation.model.LoginPresentationData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LoginUseCaseTest {
    @Mock
    private lateinit var userRepository: ILoginRepository
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        loginUseCase = LoginUseCase(userRepository)
    }

    @Test
    fun `login with valid credentials should return user presentation data`(): Unit = runBlocking {
        val username = "carlosjgr7"
        val password = "carlosjgr7$"
        val loginResponse = LoginResponse(message = "Login correcto")
        val expectedPresentationData = LoginPresentationData(name = "Login correcto")
        val data: List<String> = listOf(CONSTANTS.NUMBERDB, username, password)
        Mockito.`when`(userRepository.login(data)).thenReturn(flowOf(Result.success(loginResponse)))
        val result = loginUseCase.invoke(data)
        result.collect { actualResult ->
            Assert.assertEquals(Result.success(expectedPresentationData), actualResult)
        }
    }
}