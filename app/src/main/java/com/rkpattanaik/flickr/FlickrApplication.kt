package com.rkpattanaik.flickr

import android.app.Application
import com.facebook.stetho.Stetho

class FlickrApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this@FlickrApplication)
    }
}