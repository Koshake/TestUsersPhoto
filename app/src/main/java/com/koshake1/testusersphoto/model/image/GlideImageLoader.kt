package com.koshake1.testusersphoto.model.image

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideImageLoader : ImageLoader<ImageView> {
    override fun loadImage(
        target: ImageView,
        url: String,
        listener: ImageLoadingListener?
    ) {
        Glide.with(target)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ) = listener?.onLoadingError(e) ?: false

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ) = listener?.onLoadingSuccess() ?: false
            })
            .into(target)
    }
}