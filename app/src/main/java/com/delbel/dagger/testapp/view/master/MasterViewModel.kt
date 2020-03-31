package com.delbel.dagger.testapp.view.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delbel.dagger.rx.MainScheduler
import com.delbel.dagger.testapp.repository.TextRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import javax.inject.Inject

internal class MasterViewModel @Inject constructor(
    private val repository: TextRepository,
    @MainScheduler private val mainScheduler: Scheduler
) : ViewModel() {

    private val disposables = mutableListOf<Disposable>()

    private val _textLength = MutableLiveData<String>()
    val textLength: LiveData<String> get() = _textLength

    fun onInputChanges(input: String) {
        disposables.add(
            repository.obtainTextWithLength(input)
                .observeOn(mainScheduler)
                .subscribe { text -> _textLength.value = text })
    }

    override fun onCleared() {
        disposables.forEach { it.dispose() }
        super.onCleared()
    }
}