import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.delivery.tecnokargo.core.Resources
import com.delivery.tecnokargo.core.data.CONSTANTS
import com.delivery.tecnokargo.login.domain.LoginUseCase
import com.delivery.tecnokargo.login.presentation.model.LoginPresentationData
import com.delivery.tecnokargo.login.presentation.viewmodels.LoginViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val loginUseCase: LoginUseCase = Mockito.mock(LoginUseCase::class.java)
    private lateinit var loginViewModel: LoginViewModel
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(dispatcher, loginUseCase)
    }

    @Test
    fun `login with valid credentials should update coroutine correctly`() = runTest {
        val username = "carlosjgr7"
        val password = "carlosjgr7$"
        val loginResponse = LoginPresentationData(name = "Login correcto")
        val expectedPresentationData =
            Resources.Success(LoginPresentationData(name = "Login correcto"))

        val data: List<String> = listOf(CONSTANTS.NUMBERDB, username, password)

        whenever(loginUseCase.invoke(data)).thenReturn(flowOf(Result.success(loginResponse)))
        var emissionCount = 0
        val loginStateFlow = loginViewModel.loginState
        loginViewModel.makeLogin(username, password)
        loginStateFlow.take(2).collect { result ->
            println("Login State: $result")
            when (emissionCount) {
                0 -> {
                    Assert.assertTrue(result is Resources.Loading)
                }
                1 -> {
                    assertEquals(expectedPresentationData, result)
                }
            }
            emissionCount++
        }
    }
}