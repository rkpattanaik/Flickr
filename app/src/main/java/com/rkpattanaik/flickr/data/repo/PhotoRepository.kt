package com.rkpattanaik.flickr.data.repo

import com.rkpattanaik.flickr.data.model.PhotosPage
import io.reactivex.Single

interface PhotoRepository {

    fun getRecent(): Single<PhotosPage>

    fun search(query: String): Single<PhotosPage>
}