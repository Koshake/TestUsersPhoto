package com.koshake1.testusersphoto.model.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import android.widget.ImageView
import com.koshake1.testusersphoto.model.datasources.ImageDataSource
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource
import com.koshake1.testusersphoto.model.image.cache.ImageCache
import kotlinx.coroutines.*

class ImageLoaderImpl(
    private val imageCache: ImageCache,
    private val dataSource: ImageDataSource
) : ImageLoader<ImageView> {

    companion object {
        private const val TAG = "TAG"
    }
    private val imageLoaderCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun loadImage(target: ImageView, url: String) {
        imageLoaderCoroutineScope.launch {
            val body = dataSource.getImage(url)
            val bitmap = BitmapFactory.decodeStream(body.byteStream())
            imageCache.putBitmap(url, bitmap)
            target.setImageBitmap(bitmap)
        }
    }

    override fun showImage(target: ImageView, url: String, defaultRes: Int) {
        target.setImageResource(defaultRes)
        target.tag = url

        val bitmap: Bitmap? = imageCache.getBitmap(url)
        if (bitmap != null) {
            target.setImageBitmap(bitmap)
        } else {
            loadImage(target, url)
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.message.toString())
    }
}