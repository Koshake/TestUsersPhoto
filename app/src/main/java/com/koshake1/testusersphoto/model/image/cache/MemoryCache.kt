package com.koshake1.testusersphoto.model.image.cache

import android.graphics.Bitmap
import android.util.LruCache

class MemoryCache(private val mLruCache: LruCache<String, Bitmap>) : ImageCache {

    override fun getBitmap(url: String): Bitmap? {
        return mLruCache.get(url)
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
        mLruCache.put(url, bitmap)
    }

}