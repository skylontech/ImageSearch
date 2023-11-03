package com.example.imagesearch.view.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearch.data.repo.ResourceResult
import com.example.imagesearch.data.repo.UnsplashRepository
import com.example.imagesearch.network.UnsplashResponse
import com.example.imagesearch.utils.log
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val unsplashRepository: UnsplashRepository,
    private val saveStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val DEFAULT_QUERY = "bikes"
        private const val QUERY_KEY = "query"
    }

    private val _photosLiveData = MutableLiveData<ResourceResult<UnsplashResponse>>()
    val photosLiveData: LiveData<ResourceResult<UnsplashResponse>> = _photosLiveData

    init {
        val savedQuery = saveStateHandle[QUERY_KEY] ?: run {
            "No saved query found. Using default".log()
            DEFAULT_QUERY
        }
        searchPhotos(savedQuery)
    }

    fun searchPhotos(query: String) {
        "Searching with : $query".log()
        saveStateHandle[QUERY_KEY] = query
        _photosLiveData.postValue(ResourceResult.Loading())
        viewModelScope.launch {
            _photosLiveData.postValue(
                unsplashRepository.queryImages(query)
            )
        }
    }
}