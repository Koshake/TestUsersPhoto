package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums

interface PhotosRepository {

    suspend fun getAlbums(userId : Int) : List<UserAlbums>
    suspend fun getPhotos(albumId : Int) : List<Photo>

}