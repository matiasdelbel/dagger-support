package com.delbel.dagger.testapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.delbel.dagger.testapp.databinding.ScreenMainBinding
import com.delbel.dagger.testapp.view.detail.DetailScreen
import com.delbel.dagger.testapp.view.master.MasterScreen

internal class MainScreen : AppCompatActivity() {

    private lateinit var screenBinding: ScreenMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenBinding = ScreenMainBinding.inflate(layoutInflater )
        setContentView(screenBinding.root)

        if (savedInstanceState == null) MasterScreen.replace(screenBinding.container.id, supportFragmentManager)
    }

    fun navigateWith(input: String) = DetailScreen.replace(screenBinding.container.id, supportFragmentManager, input)
}