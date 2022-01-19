package com.koshake1.testusersphoto.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.ItemPhotoBinding
import com.koshake1.testusersphoto.model.data.photo.Photo

class PhotosAdapter(
    private var photos : List<Photo> = ArrayList()
) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rvBinding = ItemPhotoBinding.bind(itemView)
        fun bind(currentItem: Photo) {
            with(rvBinding) {
                photoText.text = currentItem.title

                photoImage.load(currentItem.url) {
                    crossfade(true)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                }
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