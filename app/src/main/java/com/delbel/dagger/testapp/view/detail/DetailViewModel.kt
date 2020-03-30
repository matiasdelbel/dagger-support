package com.delbel.dagger.testapp.view.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.delbel.dagger.testapp.di.AssistedViewModelFactory
import com.delbel.dagger.testapp.repository.TextRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class DetailViewModel @AssistedInject constructor(
    private val repository: TextRepository,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<DetailViewModel>

    val listing = repository
        .obtainTextWithLength(text = handle["parameter"]!!)
        .toFlowable()
        .toLiveData()
}