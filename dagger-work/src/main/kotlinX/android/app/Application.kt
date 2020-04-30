package android.app

import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory

fun Application.initializeWorkManager(factory: WorkerFactory) = WorkManager.initialize(
    this, Configuration.Builder()
        .setWorkerFactory(factory)
        .build()
)