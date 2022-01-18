package com.koshake1.testusersphoto.model.image

interface ImageLoadingListener {
    fun onLoadingSuccess(): Boolean
    fun onLoadingError(t: Throwable?): Boolean
}