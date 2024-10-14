package com.delivery.tecnokargo.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.delivery.tecnokargo.core.di.dispacher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


@HiltViewModel
class LoginViewModel
@Inject internal constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,

) : ViewModel() {

}