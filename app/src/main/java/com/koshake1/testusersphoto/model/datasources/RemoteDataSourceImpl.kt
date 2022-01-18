package com.koshake1.testusersphoto.model.datasources

import com.koshake1.testusersphoto.model.api.ApiService
import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.UserResponse

class RemoteDataSourceImpl(private val apiService: ApiService) :  RemoteDataSource {

    override suspend fun getUsers(): UserResponse =
        UserResponse(apiService.getUsers().await())

    override suspend fun getAlbums(userId: Int): List<UserAlbums> =
        apiService.getAlbums(userId).await()

    override suspend fun getPhotos(albumId: Int): List<Photo> =
        apiService.getPhotos(albumId).await()
}