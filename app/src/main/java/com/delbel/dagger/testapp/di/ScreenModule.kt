package com.delbel.dagger.testapp.di

import androidx.lifecycle.ViewModel
import com.delbel.dagger.testapp.view.MainScreen
import com.delbel.dagger.testapp.view.MainViewModel
import com.delbel.dagger.viewmodel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal interface ScreenModule {

    @ContributesAndroidInjector
    fun contributeMainScreen(): MainScreen

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}