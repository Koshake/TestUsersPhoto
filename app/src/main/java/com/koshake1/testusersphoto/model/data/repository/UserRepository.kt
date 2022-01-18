package com.koshake1.testusersphoto.model.data.repository

import com.koshake1.testusersphoto.model.data.user.UserResponse

interface UserRepository {
    suspend fun getUsers() : UserResponse
}