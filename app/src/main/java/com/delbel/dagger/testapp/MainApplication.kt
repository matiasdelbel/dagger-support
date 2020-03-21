package com.delbel.dagger.testapp

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.delbel.dagger.testapp.di.DaggerMainComponent
import com.delbel.dagger.testapp.di.MainComponent
import com.delbel.dagger.work.ext.initializeWorkManager
import javax.inject.Inject

class MainApplication : Application() {

    val appComponent: MainComponent by lazy {
        DaggerMainComponent.builder().application(application = this).build()
    }

    @Inject
    lateinit var factory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
        initializeWorkManager(factory)
    }

    private fun injectDependencies() = appComponent.inject(application = this)
}