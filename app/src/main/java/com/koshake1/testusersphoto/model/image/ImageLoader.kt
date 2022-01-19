package com.koshake1.testusersphoto.model.image

interface ImageLoader<T> {

    suspend fun loadImage(target : T, url : String)

    suspend fun showImage(target : T, url : String, defaultRes : Int)
}