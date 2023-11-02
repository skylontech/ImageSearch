package com.example.imagesearch.view.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.imagesearch.R
import com.example.imagesearch.data.model.UnsplashPhoto

class PhotoAdapter : RecyclerView.Adapter<PhotoViewHolder>() {

    private val itemDifferCallback = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
        override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
            return oldItem == newItem
        }
    }

    private val itemDiffer = AsyncListDiffer(this, itemDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PhotoViewHolder(layoutInflater.inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(itemDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return itemDiffer.currentList.size
    }

    fun submitList(photos: List<UnsplashPhoto>) {
        itemDiffer.submitList(photos)
    }
}

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.img_view)

    private val userName: TextView = itemView.findViewById(R.id.tv_user_name)

    fun bind(photo: UnsplashPhoto) {
        Glide.with(imageView)
            .load(photo.urls.regular)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
        userName.text = photo.user.name
    }
}