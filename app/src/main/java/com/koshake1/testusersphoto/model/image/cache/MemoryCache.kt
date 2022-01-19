package com.koshake1.testusersphoto.model.image.cache

import android.graphics.Bitmap
import android.util.LruCache

class MemoryCache(private val mLruCache: LruCache<String, Bitmap>) : ImageCache {

    override fun getBitmap(url: String): Bitmap? {
        TODO("Not yet implemented")
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
        TODO("Not yet implemented")
    }

}