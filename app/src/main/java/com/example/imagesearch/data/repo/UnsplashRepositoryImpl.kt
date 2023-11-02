package com.example.imagesearch.data.repo

import com.example.imagesearch.network.UnsplashApi
import com.example.imagesearch.network.UnsplashResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashRepository {

    override suspend fun queryImages(query: String): ResourceResult<UnsplashResponse> {
        return try {
            ResourceResult.Success(unsplashApi.searchPhotos(query))
        } catch (e: Exception) {
            ResourceResult.Error(e)
        }
    }
}