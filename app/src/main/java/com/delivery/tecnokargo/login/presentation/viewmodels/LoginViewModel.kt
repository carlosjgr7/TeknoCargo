package com.delivery.tecnokargo.login.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delivery.tecnokargo.core.Resources
import com.delivery.tecnokargo.core.data.CONSTANTS
import com.delivery.tecnokargo.core.di.dispacher.IoDispatcher
import com.delivery.tecnokargo.login.domain.LoginUseCase
import com.delivery.tecnokargo.login.presentation.model.LoginPresentationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _login =
        MutableStateFlow<Resources<LoginPresentationData>>(Resources.Loading(false))
    val loginState: StateFlow<Resources<LoginPresentationData>> get() = _login.asStateFlow()

    fun makeLogin(username: String, password: String) {
        val parameters: List<String> = listOf(CONSTANTS.NUMBERDB, username, password)
        _login.value = Resources.Loading(true)
        viewModelScope.launch(dispatcher) {
            loginUseCase(parameters).collect { result ->
                result.fold(
                    onSuccess = { value ->
                        _login.value = Resources.Success(value)
//                        Log.d("LoginViewModel", "Login succeeded with value: $value")
                    },
                    onFailure = { error ->
                        _login.value = Resources.Failure(error)
//                        Log.e("LoginViewModel", "Login failed with error: ${error.message}")
                    }
                )
            }
        }
    }

}