package com.rkpattanaik.flickr.di.module

import com.rkpattanaik.flickr.data.repo.PhotoRepository
import com.rkpattanaik.flickr.data.repo.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class,
    NetworkModule::class
])
abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun providePhotoRepository(photoRepository: PhotoRepositoryImpl): PhotoRepository
}