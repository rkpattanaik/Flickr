package com.rkpattanaik.flickr.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkpattanaik.flickr.core.AppViewModelFactory
import com.rkpattanaik.flickr.di.mapkey.ViewModelKey
import com.rkpattanaik.flickr.home.HomeViewModel
import com.rkpattanaik.flickr.search.PhotoSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoSearchViewModel::class)
    internal abstract fun bindPhotoSearchViewModel(photoSearchViewModel: PhotoSearchViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}