package com.rkpattanaik.flickr.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkpattanaik.flickr.R
import com.rkpattanaik.flickr.data.model.Photo
import com.rkpattanaik.flickr.detail.PhotoDetailActivity
import com.rkpattanaik.flickr.search.PhotoSearchActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), PhotosAdapter.OnPhotoClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initRecyclerView()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        observeViewModel()

        fabSearch.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PhotoSearchActivity::class.java))
        }
    }

    private fun initRecyclerView() {
        photosAdapter = PhotosAdapter(this@HomeActivity)

        rvPhotos.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = photosAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewModel.getPhotoListLiveData().observe(this@HomeActivity, Observer { photos ->
            if (photos == null) return@Observer
            photosAdapter.setPhotos(photos)
        })
    }

    override fun onPhotoClicked(photo: Photo) {
        Intent(this@HomeActivity, PhotoDetailActivity::class.java)
            .run {
                putExtra(PhotoDetailActivity.EXTRA_PHOTO, photo)
                startActivity(this)
            }
    }
}
