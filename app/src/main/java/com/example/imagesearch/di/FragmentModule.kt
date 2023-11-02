package com.example.imagesearch.di

import com.example.imagesearch.view.gallery.FragmentGallery
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeFragmentGallery() : FragmentGallery
}