package com.delivery.tecnokargo.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException

fun <T, E> Flow<Result<T>>.catchAndMapErrors(errorMapping: Map<String, E>): Flow<Result<T>> = catch { error ->
    if (error is Exception) {
        val errorBody = (error as? HttpException)?.response()?.errorBody()?.string()
        val mappedError = errorMapping.keys.find { key -> errorBody?.contains(key) == true }?.let { errorMapping[it] }
        if (mappedError != null) {
            emit(Result.failure(Throwable(mappedError.toString())))
        } else {
            emit(Result.failure(Throwable("UNKNOWN_ERROR")))
        }
    }
}