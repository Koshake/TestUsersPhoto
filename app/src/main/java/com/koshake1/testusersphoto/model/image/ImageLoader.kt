package com.koshake1.testusersphoto.model.image

interface ImageLoader<T> {
    fun loadImage(
        target: T,
        url: String,
        listener: ImageLoadingListener? = null
    )
}