package com.delbel.dagger.testapp

import android.app.Application
import com.delbel.dagger.testapp.di.DaggerMainComponent
import com.delbel.dagger.testapp.di.MainComponent

class MainApplication : Application() {

    val appComponent: MainComponent by lazy { DaggerMainComponent.builder().build() }
}