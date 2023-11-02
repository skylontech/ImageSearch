package com.example.imagesearch.view.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearch.data.repo.ResourceResult
import com.example.imagesearch.data.repo.UnsplashRepository
import com.example.imagesearch.network.UnsplashResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) : ViewModel() {

    companion object {
        private const val DEFAULT_QUERY = "bikes"
    }

    private val _photosLiveData = MutableLiveData<ResourceResult<UnsplashResponse>>()
    val photosLiveData: LiveData<ResourceResult<UnsplashResponse>> = _photosLiveData

    init {
        searchPhotos(DEFAULT_QUERY)
    }

    fun searchPhotos(query: String) {
        _photosLiveData.postValue(ResourceResult.Loading())
        viewModelScope.launch {
            _photosLiveData.postValue(
                unsplashRepository.queryImages(query)
            )
        }
    }
}