package com.delbel.dagger.testapp.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import com.delbel.dagger.rx.MainScheduler
import com.delbel.dagger.testapp.MainApplication
import com.delbel.dagger.testapp.R
import com.delbel.dagger.testapp.repository.TextRepository
import com.delbel.dagger.testapp.worker.NotificationWorker
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MainScreen : AppCompatActivity() {

    @Inject
    @MainScheduler
    lateinit var mainScheduler: Scheduler
    @Inject
    lateinit var repository: TextRepository

    private val disposables = mutableListOf<Disposable>()
    private lateinit var input: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.screen_main)
        input = findViewById(R.id.input)
    }

    fun testRxDagger(view: View) {
        view.isEnabled = false
        val disposable = repository.obtainTextWithLength(input.text.toString())
            .observeOn(mainScheduler)
            .subscribe { text ->
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                view.isEnabled = true
            }

        disposables.add(disposable)
    }

    fun testWorkerDagger(view: View) {
        WorkManager.getInstance(application).enqueue(NotificationWorker.workRequest())
    }

    override fun onDestroy() {
        disposables.forEach { it.dispose() }
        super.onDestroy()
    }
}