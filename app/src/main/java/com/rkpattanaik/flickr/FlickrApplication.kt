package com.rkpattanaik.flickr

import com.facebook.stetho.Stetho
import com.rkpattanaik.flickr.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class FlickrApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this@FlickrApplication)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this@FlickrApplication)
    }
}