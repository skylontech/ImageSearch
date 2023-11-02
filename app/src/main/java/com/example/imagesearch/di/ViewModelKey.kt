package com.example.imagesearch.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)