package com.rkpattanaik.flickr.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rkpattanaik.flickr.data.model.Photo
import com.rkpattanaik.flickr.data.repo.PhotoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PhotoSearchViewModel @Inject constructor(private val repository: PhotoRepository): ViewModel() {

    private val photoListLiveData = MutableLiveData<List<Photo>>()
    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun search(query: String) {
        repository.search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photosPage ->
                showProgressLiveData.value = false
                photoListLiveData.value = photosPage?.photos
            }, {
                showProgressLiveData.value = false
            })
            .let { compositeDisposable.add(it) }
    }

    fun getPhotoListLiveData() = photoListLiveData

    fun getShowProgressLiveData() = showProgressLiveData

    fun onQuerySubmit(query: String) {
        showProgressLiveData.value = true
        search(query)
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
        super.onCleared()
    }
}