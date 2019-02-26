package com.rkpattanaik.flickr.data.api

import com.rkpattanaik.flickr.data.model.RecentPhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    companion object {
        const val API_KEY = "6bf318919bbbc455f3573d18798a58e3"
        const val BASE_URL = "https://api.flickr.com/services/"
    }

    @GET("rest/?method=flickr.photos.getRecent&api_key=$API_KEY" +
            "&extras=owner_name,icon_server,icon_farm&format=json&nojsoncallback=1")
    fun getRecent(@Query("per_page") perPage: Int = 50): Single<RecentPhotosResponse>

    @GET("rest/?method=flickr.photos.search&api_key=$API_KEY" +
            "&extras=owner_name,icon_server,icon_farm&format=json&nojsoncallback=1")
    fun search(
        @Query("tags") query: String,
        @Query("per_page") perPage: Int = 50): Single<RecentPhotosResponse>
}