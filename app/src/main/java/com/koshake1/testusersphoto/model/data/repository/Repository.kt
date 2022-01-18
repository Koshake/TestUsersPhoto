package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.UserResponse

interface Repository {
    suspend fun getUsers() : UserResponse
    suspend fun getAlbums(userId : Int) : List<UserAlbums>
    suspend fun getPhotos(albumId : Int) : List<Photo>
}