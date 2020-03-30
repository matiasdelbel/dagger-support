package com.delbel.dagger.testapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delbel.dagger.testapp.R
import com.delbel.dagger.testapp.view.detail.DetailScreen
import com.delbel.dagger.testapp.view.master.MasterScreen
import kotlinx.android.synthetic.main.screen_main.*

internal class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)

        if (savedInstanceState == null) MasterScreen.replace(container.id, supportFragmentManager)
    }

    fun navigateWith(input: String) = DetailScreen.replace(container.id, supportFragmentManager, input)
}