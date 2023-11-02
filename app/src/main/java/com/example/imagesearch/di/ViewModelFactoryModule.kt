package com.example.imagesearch.di

import androidx.lifecycle.ViewModelProvider
import com.example.imagesearch.view.viewModel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}