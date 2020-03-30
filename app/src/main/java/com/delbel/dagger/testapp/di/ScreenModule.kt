package com.delbel.dagger.testapp.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.delbel.dagger.testapp.view.detail.DetailScreen
import com.delbel.dagger.testapp.view.master.MasterScreen
import com.delbel.dagger.testapp.view.master.MasterViewModel
import com.delbel.dagger.viewmodel.general.ViewModelKey
import com.delbel.dagger.viewmodel.savedstate.ViewModelFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [AssistedViewModelModule::class])
internal interface ScreenModule {

    @ContributesAndroidInjector
    fun contributeMasterScreen(): MasterScreen

    @ContributesAndroidInjector
    fun contributeDetailScreen(): DetailScreen

    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel::class)
    fun bindMainViewModel(viewModel: MasterViewModel): ViewModel
}

@AssistedModule
@Module(includes = [AssistedInject_AssistedViewModelModule::class])
interface AssistedViewModelModule

// TODO workaround for: https://github.com/square/AssistedInject/issues/81
interface AssistedViewModelFactory<T : ViewModel> : ViewModelFactory<T> {

    override fun create(handle: SavedStateHandle): T
}