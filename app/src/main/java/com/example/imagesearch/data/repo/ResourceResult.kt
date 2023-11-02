package com.example.imagesearch.data.repo

sealed interface ResourceResult<T> {
    class Loading<T>: ResourceResult<T>

    data class Success<T>(val result: T) : ResourceResult<T>

    data class Error<T>(val error: Throwable) : ResourceResult<T>
}