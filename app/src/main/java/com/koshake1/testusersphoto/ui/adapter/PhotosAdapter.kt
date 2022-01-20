package com.koshake1.testusersphoto.ui.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.ItemPhotoBinding
import com.koshake1.testusersphoto.model.data.photo.Photo
import com.koshake1.testusersphoto.model.image.ImageLoader

class PhotosAdapter(
    private var photos : List<Photo> = ArrayList(),
    private val imageLoader : ImageLoader<ImageView>
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rvBinding = ItemPhotoBinding.bind(itemView)
        fun bind(currentItem: Photo) {
            with(rvBinding) {
                photoText.text = currentItem.title

                photoImage.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                photoImage.settings.loadWithOverviewMode = true
                photoImage.settings.useWideViewPort = true

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_photo,
            parent, false
        )
        return PhotosViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    fun fillList(items: List<Photo>) {
        this.photos += items
        notifyDataSetChanged()
    }

    fun clear() {
        this.photos = emptyList()
    }
}