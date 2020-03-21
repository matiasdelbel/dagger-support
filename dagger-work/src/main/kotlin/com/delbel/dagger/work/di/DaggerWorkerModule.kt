package com.delbel.dagger.work.di

import androidx.work.WorkerFactory
import com.delbel.dagger.work.MainWorkerFactory
import dagger.Binds
import dagger.Module

@Module
interface DaggerWorkerModule {

    @Binds
    fun bindWorkerFactory(factory: MainWorkerFactory): WorkerFactory
}