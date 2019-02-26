package com.rkpattanaik.flickr.di.component

import com.rkpattanaik.flickr.FlickrApplication
import com.rkpattanaik.flickr.di.module.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class
])
interface AppComponent: AndroidInjector<FlickrApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<FlickrApplication>()
}