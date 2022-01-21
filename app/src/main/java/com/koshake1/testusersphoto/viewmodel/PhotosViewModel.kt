package com.koshake1.testusersphoto.viewmodel

import com.koshake1.testusersphoto.model.data.photo.UserPhotos
import com.koshake1.testusersphoto.model.data.viewstate.BaseState
import com.koshake1.testusersphoto.model.interactor.PhotosInteractor

class PhotosViewModel(private val interactor : PhotosInteractor) : BaseViewModel<UserPhotos>() {

    fun getPhotos(id : Int) {
        mStateLiveData.postValue(BaseState.Loading())
        runAsync {
            mStateLiveData.postValue(BaseState.Success(interactor.getAllUserPhotos(id)))
        }
    }
}