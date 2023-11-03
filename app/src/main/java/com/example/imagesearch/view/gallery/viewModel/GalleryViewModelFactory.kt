@file:Suppress("UNCHECKED_CAST")

package com.example.imagesearch.view.gallery.viewModel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.imagesearch.data.repo.UnsplashRepository
import com.example.imagesearch.view.gallery.GalleryViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class GalleryViewModelFactory @AssistedInject constructor(
    @Assisted owner: SavedStateRegistryOwner,
    private val unsplashRepository: UnsplashRepository
) : AbstractSavedStateViewModelFactory(owner, Bundle()) {

    @dagger.assisted.AssistedFactory
    fun interface AssistedFactory {
        fun create(savedStateRegistryOwner: SavedStateRegistryOwner) : GalleryViewModelFactory
    }
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return if (modelClass == GalleryViewModel::class.java) {
            GalleryViewModel(unsplashRepository, handle) as T
        } else {
            error("This factory can only create GalleryViewModel")
        }
    }
}