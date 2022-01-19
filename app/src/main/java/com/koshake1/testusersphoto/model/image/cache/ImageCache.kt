package com.koshake1.testusersphoto.model.image.cache

import android.graphics.Bitmap

interface ImageCache {
    fun getBitmap(url : String) : Bitmap?

    fun putBitmap(url : String, bitmap : Bitmap)
}