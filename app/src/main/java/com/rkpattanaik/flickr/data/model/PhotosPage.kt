package com.rkpattanaik.flickr.data.model

import com.google.gson.annotations.SerializedName

data class PhotosPage(
    val page: Int,
    @SerializedName("pages")
    val totalNoOfPages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val total: Int,
    @SerializedName("photo")
    val photos: List<Photo>
)