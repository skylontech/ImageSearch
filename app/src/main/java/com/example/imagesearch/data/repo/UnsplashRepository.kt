package com.example.imagesearch.data.repo

import com.example.imagesearch.network.UnsplashResponse

interface UnsplashRepository {

    suspend fun queryImages(query: String) : ResourceResult<UnsplashResponse>
}