package com.delbel.dagger.testapp.di

import android.app.Application
import com.delbel.dagger.rx.di.DaggerRxModule
import com.delbel.dagger.testapp.MainApplication
import com.delbel.dagger.testapp.view.MainScreen
import com.delbel.dagger.work.di.DaggerWorkerModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DaggerRxModule::class,
        DaggerWorkerModule::class,
        NotificationModule::class,
        WorkerBindingModule::class
    ]
)
interface MainComponent {

    fun inject(application: MainApplication)

    fun inject(app: MainScreen)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}