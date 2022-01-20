package com.koshake1.testusersphoto.model.image.cache

import android.graphics.Bitmap
import android.util.LruCache

class MemoryCache : ImageCache {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    val cacheSize = maxMemory / 8

    private val mLruCache = object : LruCache<String, Bitmap>(cacheSize) {

        override fun sizeOf(key: String, bitmap: Bitmap): Int {
            return bitmap.byteCount / 1024
        }
    }

    override fun getBitmap(url: String): Bitmap? {
        return mLruCache.get(url)
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
        mLruCache.put(url, bitmap)
    }
}