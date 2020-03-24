package com.delbel.dagger.work.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.reflect.KClass

@MapKey
@Target(FUNCTION)
@Retention(RUNTIME)
annotation class WorkerKey(val key: KClass<out ListenableWorker>)