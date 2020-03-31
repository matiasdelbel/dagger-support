package com.delbel.dagger.testapp.view.master

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.delbel.dagger.testapp.R
import com.delbel.dagger.testapp.view.MainScreen
import com.delbel.dagger.testapp.view.detail.DetailScreen
import com.delbel.dagger.testapp.worker.NotificationWorker
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.screen_master.*
import javax.inject.Inject

internal class MasterScreen : Fragment(R.layout.screen_master) {

    companion object {

        fun replace(@IdRes containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager
                .beginTransaction()
                .replace(containerId, MasterScreen(), MasterScreen::class.java.name)
                .commit()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MasterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.textLength.observe(viewLifecycleOwner, Observer { text ->
            Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
            calculateLength.isEnabled = true
        })


        calculateLength.setOnClickListener {
            calculateLength.isEnabled = false
            viewModel.onInputChanges(input.text.toString())
        }

        throwNotification.setOnClickListener {
            WorkManager.getInstance(requireContext()).enqueue(NotificationWorker.workRequest())
        }

        navigateAndCalculateLength.setOnClickListener {
            (requireActivity() as MainScreen).navigateWith(input = input.text.toString())
        }
    }
}