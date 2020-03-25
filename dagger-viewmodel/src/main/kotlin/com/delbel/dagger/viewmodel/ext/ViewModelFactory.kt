package com.delbel.dagger.viewmodel.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> Factory.create(activity: FragmentActivity, vmClass: Class<T>): T =
    ViewModelProviders.of(activity, this)[vmClass]

fun <T : ViewModel> Factory.create(fragment: Fragment, vmClass: Class<T>): T =
    ViewModelProviders.of(fragment, this)[vmClass]
