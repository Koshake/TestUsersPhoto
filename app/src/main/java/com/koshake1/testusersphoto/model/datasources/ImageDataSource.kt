package com.koshake1.testusersphoto.model.datasources

import okhttp3.ResponseBody


interface ImageDataSource {
    suspend fun getImage(imageUrl : String) : ResponseBody
}