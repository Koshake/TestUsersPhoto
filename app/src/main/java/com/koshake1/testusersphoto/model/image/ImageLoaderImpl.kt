package com.koshake1.testusersphoto.model.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import android.widget.ImageView
import com.koshake1.testusersphoto.model.datasources.RemoteDataSource
import com.koshake1.testusersphoto.model.image.cache.ImageCache

class ImageLoaderImpl(
    private val imageCache: ImageCache,
    private val dataSource: RemoteDataSource
) : ImageLoader<ImageView> {

    override suspend fun loadImage(target: ImageView, url: String) {
        val body = dataSource.getImages(url)

        val bitmap = BitmapFactory.decodeStream(body.byteStream())
        imageCache.putBitmap(url, bitmap)
        target.setImageBitmap(bitmap)
    }

    override suspend fun showImage(target: ImageView, url: String, defaultRes: Int) {
        target.setImageResource(defaultRes)
        target.tag = url

        val bitmap: Bitmap? = imageCache.getBitmap(url)
        if (bitmap != null) {
            target.setImageBitmap(bitmap)
        } else {
            loadImage(target, url)
        }
    }
}