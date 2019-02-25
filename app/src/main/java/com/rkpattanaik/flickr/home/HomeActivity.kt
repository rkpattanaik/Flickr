package com.rkpattanaik.flickr.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkpattanaik.flickr.R
import com.rkpattanaik.flickr.data.model.Photo
import com.rkpattanaik.flickr.detail.PhotoDetailActivity
import com.rkpattanaik.flickr.search.PhotoSearchActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity(), PhotosAdapter.OnPhotoClickListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
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
