package com.koshake1.testusersphoto.model.api

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceImage {
    @GET("{imageUrl}")
    fun getImage(@Path("imageUrl") image : String
    ) : Deferred<ResponseBody>
}