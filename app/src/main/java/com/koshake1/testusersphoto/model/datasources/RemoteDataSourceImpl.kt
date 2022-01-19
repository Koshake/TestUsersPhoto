package com.koshake1.testusersphoto.model.datasources

import com.koshake1.testusersphoto.model.api.ApiService
import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.UserResponse
import okhttp3.ResponseBody

class RemoteDataSourceImpl(private val apiService: ApiService) :  RemoteDataSource {

    override suspend fun getUsers() =
        UserResponse(apiService.getUsers().await())

    override suspend fun getAlbums(userId: Int) =
        apiService.getAlbums(userId).await()

    override suspend fun getPhotos(albumId: Int) =
        apiService.getPhotos(albumId).await()

    override suspend fun getImages(num: String) =
        apiService.getImage(num).await()
}