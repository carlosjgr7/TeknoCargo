package com.delivery.tecnokargo.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException

fun <T> Flow<Result<T>>.error(
    errorMessageMapper: (String.() -> String?)? = null,
): Flow<Result<T>> = catch { error ->
    val throwableMessage = (error as? HttpException)?.response()?.errorBody()?.string()
        ?.let { errorBody ->
            errorMessageMapper?.invoke(errorBody) ?: ErrorGeneric.UNKNOWN.name
        } ?: ErrorGeneric.UNKNOWN.name
    emit(Result.failure(Throwable(throwableMessage)))
}


interface Error
enum class ErrorGeneric: Error {
    UNKNOWN,
}
