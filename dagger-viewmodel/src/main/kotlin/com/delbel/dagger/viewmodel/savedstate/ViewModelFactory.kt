package com.delbel.dagger.viewmodel.savedstate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface ViewModelFactory<T : ViewModel> {

    fun create(handle: SavedStateHandle): T
}