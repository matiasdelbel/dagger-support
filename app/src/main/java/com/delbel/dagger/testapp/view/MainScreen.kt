package com.delbel.dagger.testapp.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.delbel.dagger.testapp.R
import com.delbel.dagger.testapp.worker.NotificationWorker
import com.delbel.dagger.viewmodel.ext.create
import dagger.android.AndroidInjection
import javax.inject.Inject

internal class MainScreen : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private lateinit var input: EditText
    private lateinit var calculateLength: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.screen_main)
        input = findViewById(R.id.input)
        calculateLength = findViewById(R.id.calculateLength)

        viewModel = viewModelFactory.create(this, MainViewModel::class.java)
        viewModel.textLength.observe(this, Observer { text ->
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
            calculateLength.isEnabled = true
        })
    }

    fun testRxDagger(view: View) {
        calculateLength.isEnabled = false
        viewModel.onInputChanges(input.text.toString())
    }

    fun testWorkerDagger(view: View) {
        WorkManager.getInstance(application).enqueue(NotificationWorker.workRequest())
    }
}