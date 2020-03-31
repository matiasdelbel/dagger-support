package com.delbel.dagger.testapp.view.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.delbel.dagger.testapp.R
import com.delbel.dagger.viewmodel.savedstate.ext.viewModels
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.screen_detail.*
import javax.inject.Inject

class DetailScreen : Fragment(R.layout.screen_detail) {

    companion object {

        fun replace(@IdRes containerId: Int, fragmentManager: FragmentManager, input: String) {
            val fragment = DetailScreen().apply { arguments = bundleOf("parameter" to input) }

            fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, DetailScreen::class.java.name)
                .commit()
        }
    }

    @Inject
    internal lateinit var factory: DetailViewModel.Factory
    private val viewModel: DetailViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listing.observe(viewLifecycleOwner, Observer { result.text = it })
    }
}