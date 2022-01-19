package com.koshake1.testusersphoto.model.datasources

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.UserResponse
import okhttp3.ResponseBody

interface RemoteDataSource {
    suspend fun getUsers() : UserResponse

    suspend fun getAlbums(userId : Int) : List<UserAlbums>

    suspend fun getPhotos(albumId : Int) : List<Photo>

    suspend fun getImages(num : String) : ResponseBody
}