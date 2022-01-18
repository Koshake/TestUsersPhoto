package com.koshake1.testusersphoto.model.datasources

import com.koshake1.testusersphoto.model.api.ApiService
import com.koshake1.testusersphoto.model.data.Photo
import com.koshake1.testusersphoto.model.data.User
import com.koshake1.testusersphoto.model.data.UserAlbums

class RemoteDataSourceImpl(private val apiService: ApiService) :  RemoteDataSource {

    override suspend fun getUsers(): List<User> =
        apiService.getUsers().await()


    override suspend fun getAlbums(userId: Int): List<UserAlbums> =
        apiService.getAlbums().await()

    override suspend fun getPhotos(albumId: Int): List<Photo> =
        apiService.getPhotos().await()
}