package com.delbel.dagger.work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ListenableWorkerFactory {

    fun create(appContext: Context, params: WorkerParameters): ListenableWorker
}