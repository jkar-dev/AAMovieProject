package com.jkapps.aamovieproject.base

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val output : T) : Result<T>()
    data class Error(val exception: Exception)  : Result<Nothing>()
}
