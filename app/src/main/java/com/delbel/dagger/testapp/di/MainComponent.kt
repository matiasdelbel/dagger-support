package com.delbel.dagger.testapp.di

import com.delbel.dagger.rx.di.DaggerRxModule
import com.delbel.dagger.testapp.view.MainScreen
import dagger.Component

@Component(modules = [DaggerRxModule::class])
interface MainComponent {

    fun inject(app: MainScreen)

    @Component.Builder
    interface Builder {

        fun build(): MainComponent
    }
}