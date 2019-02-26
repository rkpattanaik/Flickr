package com.rkpattanaik.flickr.data.repo

import com.rkpattanaik.flickr.data.api.FlickrApi
import com.rkpattanaik.flickr.data.model.PhotosPage
import io.reactivex.Single
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(val flickrApi: FlickrApi) : PhotoRepository {

    override fun getRecent(): Single<PhotosPage> {
        return flickrApi.getRecent()
                    .map { it.photosPage }
    }

    override fun search(query: String): Single<PhotosPage> {
        return flickrApi.search(query)
            .map { it.photosPage }
    }
}