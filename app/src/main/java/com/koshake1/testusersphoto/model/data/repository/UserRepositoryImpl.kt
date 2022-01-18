package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.UserResponse
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource

class UserRepositoryImpl(private val dataSource : RemoteDataSource) : UserRepository {

    override suspend fun getUsers(): UserResponse =
        dataSource.getUsers()

}