package com.koshake1.testusersphoto.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
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

                photoImage.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                photoImage.settings.loadWithOverviewMode = true
                photoImage.settings.useWideViewPort = true

                photoImage.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        view.loadUrl(url)
                        return false
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                        progressBar.visibility = View.GONE
                    }
                }
                photoImage.loadUrl(currentItem.url)
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