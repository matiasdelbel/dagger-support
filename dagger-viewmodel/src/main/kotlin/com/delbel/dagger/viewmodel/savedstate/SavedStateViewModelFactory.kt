package com.delbel.dagger.viewmodel.savedstate

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class SavedStateViewModelFactory<T : ViewModel>(
    private val viewModelFactory: ViewModelFactory<T>,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = viewModelFactory.create(handle) as T
}