package com.delbel.dagger.testapp.di

import com.delbel.dagger.testapp.worker.NotificationWorker
import com.delbel.dagger.work.ListenableWorkerFactory
import com.delbel.dagger.work.di.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(NotificationWorker::class)
    fun bindDatabasePopulateWorker(factory: NotificationWorker.Factory): ListenableWorkerFactory
}