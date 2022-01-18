package com.koshake1.testusersphoto.model.api

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.User
import com.koshake1.testusersphoto.model.data.user.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {
    @GET("/users")
    fun getUsers(
    ) : Deferred<List<User>>

    @GET("/users/{id}/albums")
    fun getAlbums(
    ) : Deferred<List<UserAlbums>>

    @GET("/albums/{id}/photos")
    fun getPhotos(
    ) : Deferred<List<Photo>>
}