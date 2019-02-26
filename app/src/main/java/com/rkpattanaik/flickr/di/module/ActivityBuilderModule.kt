package com.rkpattanaik.flickr.di.module

import com.rkpattanaik.flickr.home.HomeActivity
import com.rkpattanaik.flickr.search.PhotoSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributePhotSearchActivity(): PhotoSearchActivity
}