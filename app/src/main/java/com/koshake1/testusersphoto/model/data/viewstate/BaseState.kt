package com.koshake1.testusersphoto.model.data.viewstate

sealed class BaseState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : BaseState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : BaseState<T>()
    class Loading<out T : AppStateEntity> : BaseState<T>()
}