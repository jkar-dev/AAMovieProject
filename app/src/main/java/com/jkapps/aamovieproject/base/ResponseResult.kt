package com.jkapps.aamovieproject.base

sealed class ResponseResult<out T> {
    data class Success<out T>(val output : T) : ResponseResult<T>()
    data class Error(val exception: Exception)  : ResponseResult<Nothing>()
}
