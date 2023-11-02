package com.example.imagesearch.di

import com.example.imagesearch.data.repo.UnsplashRepository
import com.example.imagesearch.data.repo.UnsplashRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepoModule {

    @Binds
    @Singleton
    fun bindsUnsplashRepository(unsplashRepositoryImpl: UnsplashRepositoryImpl) : UnsplashRepository
}