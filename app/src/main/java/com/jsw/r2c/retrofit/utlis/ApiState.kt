package com.jsw.r2c.retrofit.utlis


sealed class ApiState<out T> {
    class Success<T>(val data: T) : ApiState<T>()
    class Failure(val msg: Any) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()

    object Empty : ApiState<Nothing>()
}