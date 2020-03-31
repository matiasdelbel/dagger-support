package com.delbel.dagger.viewmodel.general.di

import androidx.lifecycle.ViewModelProvider
import com.delbel.dagger.viewmodel.general.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}