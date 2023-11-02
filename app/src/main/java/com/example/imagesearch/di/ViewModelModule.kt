package com.example.imagesearch.di

import androidx.lifecycle.ViewModel
import com.example.imagesearch.view.gallery.GalleryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @ViewModelKey(GalleryViewModel::class)
    @IntoMap
    abstract fun bindLoginViewModel(loginViewModel: GalleryViewModel) : ViewModel
}