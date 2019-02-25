package com.rkpattanaik.flickr.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkpattanaik.flickr.R
import com.rkpattanaik.flickr.data.model.Photo
import com.rkpattanaik.flickr.detail.PhotoDetailActivity
import com.rkpattanaik.flickr.home.PhotosAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class PhotoSearchActivity : AppCompatActivity(), PhotosAdapter.OnPhotoClickListener {

    private lateinit var viewModel: PhotoSearchViewModel
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_search)
        setSupportActionBar(toolbar)

        initRecyclerView()
        viewModel = ViewModelProviders.of(this).get(PhotoSearchViewModel::class.java)
        observeViewModel()
    }

    private fun initRecyclerView() {
        photosAdapter = PhotosAdapter(this@PhotoSearchActivity)

        rvPhotos.apply {
            layoutManager = LinearLayoutManager(this@PhotoSearchActivity)
            adapter = photosAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewModel.getPhotoListLiveData().observe(this@PhotoSearchActivity, Observer { photos ->
            if (photos == null) return@Observer
            photosAdapter.setPhotos(photos)
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (query.isNotBlank()) viewModel.onQuerySubmit(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val menuItem = menu?.findItem(R.id.app_bar_search)?.apply {
            expandActionView()
            setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                    return true
                }

                override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                    this@PhotoSearchActivity.finish()
                    return true
                }
            })
        }

        val searchView = (menuItem?.actionView as SearchView).apply {
            queryHint = "Search images..."
            setIconifiedByDefault(false)
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    override fun onPhotoClicked(photo: Photo) {
        Intent(this@PhotoSearchActivity, PhotoDetailActivity::class.java)
            .run {
                putExtra(PhotoDetailActivity.EXTRA_PHOTO, photo)
                startActivity(this)
            }
    }

}
