package com.rkpattanaik.flickr.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val farm: Int,
    val secret: String,
    val server: String,
    val title: String,
    val owner: String,
    @SerializedName("ownername")
    val ownerName: String,
    @SerializedName("iconfarm")
    val iconFarm: Int,
    @SerializedName("iconserver")
    val iconServer: String
) : Parcelable {
    fun getPhotoUrl() = "https://farm$farm.staticflickr.com/$server/${id}_$secret.jpg"
    fun getOwnerPhotoUrl() = "http://farm$iconFarm.staticflickr.com/$iconServer/buddyicons/$owner.jpg"
}