package com.rkpattanaik.flickr.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rkpattanaik.flickr.R
import com.rkpattanaik.flickr.data.model.Photo
import de.hdodenhof.circleimageview.CircleImageView

class PhotosAdapter(private val onPhotoClickListener: OnPhotoClickListener) : RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    private var photos: List<Photo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]

        photo.run {
            Glide.with(holder.ivPhoto.context)
                .load(getPhotoUrl())
                .into(holder.ivPhoto)

            Glide.with(holder.civPersonPhoto.context)
                .load(getOwnerPhotoUrl())
                .error(R.drawable.profile_pic_default)
                .into(holder.civPersonPhoto)

            holder.tvPersonName.text = ownerName
            holder.ivPhoto.setOnClickListener { onPhotoClickListener.onPhotoClicked(this) }
        }
    }

    override fun getItemCount() = photos.size

    fun setPhotos(photos: List<Photo>) {
        this@PhotosAdapter.photos = photos
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto = itemView.findViewById<ImageView>(R.id.ivPhoto)
        val civPersonPhoto = itemView.findViewById<CircleImageView>(R.id.civPersonPhoto)
        val tvPersonName = itemView.findViewById<TextView>(R.id.tvPersonName)
    }

    interface OnPhotoClickListener {
        fun onPhotoClicked(photo: Photo)
    }
}