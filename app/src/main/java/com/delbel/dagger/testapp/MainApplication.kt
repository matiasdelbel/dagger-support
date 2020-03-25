package com.delbel.dagger.testapp

import android.app.Application
import androidx.work.WorkerFactory
import com.delbel.dagger.testapp.di.DaggerMainComponent
import com.delbel.dagger.work.ext.initializeWorkManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
        initializeWorkManager(factory)
    }

    override fun androidInjector() = androidInjector

    private fun injectDependencies() = DaggerMainComponent.builder()
        .application(application = this)
        .build()
        .inject(application = this)
}