package com.koshake1.testusersphoto.model.data.viewstate

sealed class BaseState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : BaseState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : BaseState<T>()
    data class Loading<out T : AppStateEntity>(val isLoading: Boolean) : BaseState<T>()
}