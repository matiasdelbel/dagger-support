package com.delbel.dagger.rx

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class ComputationScheduler

@Qualifier
@Retention(RUNTIME)
annotation class IOScheduler

@Qualifier
@Retention(RUNTIME)
annotation class MainScheduler