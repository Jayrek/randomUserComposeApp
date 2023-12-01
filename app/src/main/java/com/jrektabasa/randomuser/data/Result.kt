package com.jrektabasa.randomuser.data

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data class Error(val message: String) : Result<Nothing>
}

fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}
