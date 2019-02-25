package com.rkpattanaik.flickr.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkpattanaik.flickr.data.model.Photo
import com.rkpattanaik.flickr.data.repo.PhotoRepository
import com.rkpattanaik.flickr.data.repo.PhotoRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel: ViewModel() {

    private val photoListLiveData = MutableLiveData<List<Photo>>()
    private val repository: PhotoRepository = PhotoRepositoryImpl()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        getRecentPhotos()
    }

    private fun getRecentPhotos() {
        repository.getRecent()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { photosPage -> photoListLiveData.value = photosPage?.photos }
            .let { compositeDisposable.add(it) }
    }

    fun getPhotoListLiveData() = photoListLiveData

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }
}