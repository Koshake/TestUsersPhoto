package com.koshake1.testusersphoto.model.interactor

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserPhotos
import com.koshake1.testusersphoto.model.data.repository.PhotosRepository

class PhotosInteractorImpl(private val repository : PhotosRepository) : PhotosInteractor {

    private val photos = arrayListOf<Photo>()

    override suspend fun getAllUserPhotos(userId : Int): UserPhotos {

        repository.getAlbums(userId).forEach {
            photos.addAll(repository.getPhotos(it.id))
        }

        return UserPhotos(photos)
    }
}