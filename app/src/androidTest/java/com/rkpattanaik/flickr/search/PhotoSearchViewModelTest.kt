package com.rkpattanaik.flickr.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.rkpattanaik.flickr.data.repo.PhotoRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotoSearchViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: PhotoRepository = mock()
    private lateinit var viewModel: PhotoSearchViewModel

    @Before
    fun setup() {
        viewModel = PhotoSearchViewModel(repository)
    }

    @Test
    fun onSubmitSearhQuery_SetsShowProgressLiveDataValueToTrue() {
        val showProgressObserver = mock<Observer<Boolean>>()
        viewModel.getShowProgressLiveData().observeForever(showProgressObserver)
        viewModel.onQuerySubmit("query")
        verify(showProgressObserver).onChanged(true)
    }
}