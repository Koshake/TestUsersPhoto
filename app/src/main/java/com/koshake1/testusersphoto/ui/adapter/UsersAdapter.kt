package com.koshake1.testusersphoto.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koshake1.testusersphoto.R
import com.koshake1.testusersphoto.databinding.ItemUserBinding
import com.koshake1.testusersphoto.model.data.user.User

class UsersAdapter(
    private var items : List<User> = ArrayList(),
    val clickListener : OnListItemClickListener
) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rvBinding = ItemUserBinding.bind(itemView)
        fun bind(currentItem: User) {
            with(rvBinding) {
                userNameTv.text = currentItem.username

                image.setOnClickListener { clickListener.onItemClick(currentItem.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_user,
            parent, false
        )
        return UsersViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun fillList(items: List<User>) {
        this.items += items
        notifyDataSetChanged()
    }

    fun clear() {
        this.items = emptyList()
    }
}