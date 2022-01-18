package com.koshake1.testusersphoto.viewmodel

import com.koshake1.testusersphoto.model.data.repository.Repository
import com.koshake1.testusersphoto.model.data.user.UserResponse
import com.koshake1.testusersphoto.model.data.viewstate.BaseState

class UsersViewModel(private val repository: Repository) : BaseViewModel<UserResponse>() {

    fun getUsers() {
        mStateLiveData.postValue(BaseState.Loading(true))
        runAsync {
            mStateLiveData.postValue(BaseState.Success(repository.getUsers()))
        }
    }
}