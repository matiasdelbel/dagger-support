package com.delbel.dagger.testapp.di

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat.getSystemService
import dagger.Module
import dagger.Provides

@Module
class NotificationModule {

    @Provides
    fun provideNotificationManager(application: Application) =
        getSystemService(application, NotificationManager::class.java)
}