package com.delbel.dagger.testapp.repository

import com.delbel.dagger.rx.ComputationScheduler
import io.reactivex.Scheduler
import io.reactivex.Single
import java.lang.Thread.sleep
import javax.inject.Inject

class TextRepository @Inject constructor(@ComputationScheduler private val scheduler: Scheduler) {

    fun obtainTextWithLength(text: String) = produceWithDelay(text)
        .subscribeOn(scheduler)
        .map { "Length of '$it' is ${it.length}" }

    private fun produceWithDelay(text: String) = Single.fromCallable {
        sleep(5000)
        text
    }
}