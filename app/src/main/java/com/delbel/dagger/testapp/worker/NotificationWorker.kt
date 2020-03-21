package com.delbel.dagger.testapp.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.delbel.dagger.testapp.R
import com.delbel.dagger.work.ListenableWorkerFactory
import javax.inject.Inject
import javax.inject.Provider

class NotificationWorker(
    appContext: Context,
    params: WorkerParameters,
    private val notificationManager: NotificationManager
) : Worker(appContext, params) {

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "notify-letter"
        private const val NOTIFICATION_ID = 32

        fun workRequest() = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .addTag(NotificationWorker::class.java.name)
            .build()
    }

    override fun doWork(): Result {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setContentTitle(applicationContext.getString(R.string.app_notify_title))
            .setContentText(applicationContext.getString(R.string.app_notify_content))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(NOTIFICATION_ID, builder.build())
        }

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = applicationContext.getString(R.string.app_notify_channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)

            notificationManager.createNotificationChannel(channel)
        }
    }

    class Factory @Inject constructor(
        private val notificationManagerProvider: Provider<NotificationManager>
    ) : ListenableWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker =
            NotificationWorker(appContext, params, notificationManagerProvider.get())
    }
}