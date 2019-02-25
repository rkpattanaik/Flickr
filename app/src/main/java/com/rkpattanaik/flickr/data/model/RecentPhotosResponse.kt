package com.rkpattanaik.flickr.data.model

import com.google.gson.annotations.SerializedName

data class RecentPhotosResponse(
    @SerializedName("photos")
    val photosPage: PhotosPage,
    @SerializedName("stat")
    val status: String
)