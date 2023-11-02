package com.example.imagesearch.view.gallery

import androidx.lifecycle.ViewModel
import com.example.imagesearch.data.repo.UnsplashRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
): ViewModel(){
}