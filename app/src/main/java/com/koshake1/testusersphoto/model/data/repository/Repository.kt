package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.Photo
import com.koshake1.testusersphoto.model.data.User
import com.koshake1.testusersphoto.model.data.UserAlbums

interface Repository {
    suspend fun getUsers() : List<User>
    suspend fun getAlbums(userId : Int) : List<UserAlbums>
    suspend fun getPhotos(albumId : Int) : List<Photo>
}