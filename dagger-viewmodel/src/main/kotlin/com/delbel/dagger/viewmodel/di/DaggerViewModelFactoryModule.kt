package com.delbel.dagger.viewmodel.di

import androidx.lifecycle.ViewModelProvider
import com.delbel.dagger.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface DaggerViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}