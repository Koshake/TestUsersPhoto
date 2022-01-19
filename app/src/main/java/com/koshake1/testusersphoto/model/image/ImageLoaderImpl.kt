package com.koshake1.testusersphoto.model.image

import android.widget.ImageView
import com.koshake1.testusersphoto.model.image.cache.ImageCache
import android.graphics.Bitmap

class ImageLoaderImpl(private val imageCache: ImageCache) : ImageLoader<ImageView> {

    override fun loadImage(target: ImageView, url: String) {

        }

    override fun showImage(target: ImageView, url: String, defaultRes : Int) {
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