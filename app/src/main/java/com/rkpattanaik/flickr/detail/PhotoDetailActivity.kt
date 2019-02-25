package com.rkpattanaik.flickr.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rkpattanaik.flickr.R
import com.rkpattanaik.flickr.data.model.Photo
import kotlinx.android.synthetic.main.activity_photo_detail.*

class PhotoDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PHOTO = "EXTRA_PHOTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)

        intent.getParcelableExtra<Photo>(EXTRA_PHOTO)
            .run {
                Glide.with(this@PhotoDetailActivity)
                    .load(getPhotoUrl())
                    .into(ivPhoto)

                tvPhotoTitle.text = title

                Glide.with(this@PhotoDetailActivity)
                    .load(getOwnerPhotoUrl())
                    .error(R.drawable.profile_pic_default)
                    .into(civPersonPhoto)

                tvPersonName.text = ownerName
            }
    }
}
