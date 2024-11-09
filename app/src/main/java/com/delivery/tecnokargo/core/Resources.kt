package com.delivery.tecnokargo.core

sealed class Resources<out T> {
    data class Loading<out T>(val data:Boolean) : Resources<T>()
    data class Success<out T>(val data: T) : Resources<T>()
    data class Failure(val exeption: Throwable) : Resources<Nothing>()
}
