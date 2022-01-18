package com.koshake1.testusersphoto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koshake1.testusersphoto.model.data.viewstate.AppStateEntity
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import kotlinx.coroutines.*

abstract class BaseViewModel<D : AppStateEntity>(
) : ViewModel() {

    protected val mStateLiveData = MutableLiveData<BaseState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<BaseState<D>>

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handleError(error: Throwable) {
        mStateLiveData.postValue(BaseState.Error(error))
    }

    protected fun runAsync(block: suspend () -> Unit) =
        viewModelCoroutineScope.launch {
            block()
        }
}
