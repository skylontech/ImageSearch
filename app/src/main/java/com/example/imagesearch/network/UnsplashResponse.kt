package com.example.imagesearch.network

import com.example.imagesearch.data.model.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)
