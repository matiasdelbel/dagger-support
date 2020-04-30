package com.delbel.dagger.testapp.di

import android.app.Application
import com.delbel.dagger.rx.di.DaggerRxModule
import com.delbel.dagger.testapp.MainApplication
import com.delbel.dagger.viewmodel.general.di.ViewModelFactoryModule
import com.delbel.dagger.work.di.DaggerWorkerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DaggerRxModule::class,
        DaggerWorkerModule::class,
        ViewModelFactoryModule::class,
        NotificationModule::class,
        WorkerBindingModule::class,
        ScreenModule::class
    ]
)
@Singleton
interface MainComponent {

    fun inject(application: MainApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent
    }
}