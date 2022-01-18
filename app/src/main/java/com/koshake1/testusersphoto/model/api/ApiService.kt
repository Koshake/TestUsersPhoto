package com.koshake1.testusersphoto.model.api

import com.koshake1.testusersphoto.model.data.Photo
import com.koshake1.testusersphoto.model.data.User
import com.koshake1.testusersphoto.model.data.UserAlbums
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