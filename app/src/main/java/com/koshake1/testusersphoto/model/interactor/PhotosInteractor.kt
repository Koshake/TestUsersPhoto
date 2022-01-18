package com.koshake1.testusersphoto.model.interactor

import com.koshake1.testusersphoto.model.data.photo.UserPhotos

interface PhotosInteractor {

    suspend fun getAllUserPhotos(id : Int) : UserPhotos
}