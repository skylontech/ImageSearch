package com.example.imagesearch.data.repo

import com.example.imagesearch.network.UnsplashApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashRepository {
}