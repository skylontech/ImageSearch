package com.example.imagesearch.di

import com.example.imagesearch.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun contributeMainActivity() : MainActivity
}