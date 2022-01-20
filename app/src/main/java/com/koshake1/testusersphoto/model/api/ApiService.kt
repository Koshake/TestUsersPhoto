package com.koshake1.testusersphoto.model.api

import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.data.photo.UserAlbums
import com.koshake1.testusersphoto.model.data.user.User
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    fun getUsers(
    ) : Deferred<List<User>>

    @GET("/users/{id}/albums")
    fun getAlbums(@Path("id") id: Int
    ) : Deferred<List<UserAlbums>>

    @GET("/albums/{id}/photos")
    fun getPhotos(@Path("id") id: Int
    ) : Deferred<List<Photo>>
}