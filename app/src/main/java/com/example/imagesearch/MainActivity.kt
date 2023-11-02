package com.example.imagesearch

import android.os.Bundle
import com.example.imagesearch.view.gallery.FragmentGallery
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, FragmentGallery(), "gallery")
            .commit()
    }
}