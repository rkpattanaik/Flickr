package com.rkpattanaik.flickr.data.repo

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.rkpattanaik.flickr.data.api.FlickrApi
import com.rkpattanaik.flickr.data.model.PhotosPage
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PhotoRepositoryImpl : PhotoRepository {
    private val okHttpClient = OkHttpClient.Builder()
                                .addNetworkInterceptor(StethoInterceptor())
                                .build()

    private val flickrApi = Retrofit.Builder()
                                .client(okHttpClient)
                                .baseUrl("https://api.flickr.com/services/")
                                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(FlickrApi::class.java)

    override fun getRecent(): Single<PhotosPage> {
        return flickrApi.getRecent()
                    .map { it.photosPage }
    }

    override fun search(query: String): Single<PhotosPage> {
        return flickrApi.search(query)
            .map { it.photosPage }
    }
}