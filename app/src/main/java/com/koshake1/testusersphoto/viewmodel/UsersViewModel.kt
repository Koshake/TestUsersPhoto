package com.koshake1.testusersphoto.viewmodel

import com.koshake1.testusersphoto.model.data.repository.UserRepository
import com.koshake1.testusersphoto.model.data.user.UserResponse
import com.koshake1.testusersphoto.model.data.viewstate.BaseState

class UsersViewModel(private val repository: UserRepository) : BaseViewModel<UserResponse>() {

    fun getUsers() {
        mStateLiveData.postValue(BaseState.Loading())
        runAsync {
            mStateLiveData.postValue(BaseState.Success(repository.getUsers()))
        }
    }
}