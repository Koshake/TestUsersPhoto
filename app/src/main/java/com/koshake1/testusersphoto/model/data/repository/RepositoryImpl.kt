package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.Photo
import com.koshake1.testusersphoto.model.data.User
import com.koshake1.testusersphoto.model.data.UserAlbums
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource

class RepositoryImpl(private val dataSource : RemoteDataSource) : Repository {

    override suspend fun getUsers(): List<User> =
        dataSource.getUsers()

    override suspend fun getAlbums(userId: Int): List<UserAlbums> =
        dataSource.getAlbums(userId)

    override suspend fun getPhotos(albumId: Int): List<Photo> =
        dataSource.getPhotos(albumId)
}