package android.fragment.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.delbel.dagger.viewmodel.savedstate.SavedStateViewModelFactory
import com.delbel.dagger.viewmodel.savedstate.ViewModelFactory

inline fun <reified T : ViewModel> Fragment.viewModels(
    crossinline viewModelFactoryProducer: () -> ViewModelFactory<T>
): Lazy<T> = viewModels(factoryProducer = {
    SavedStateViewModelFactory(
        viewModelFactory = viewModelFactoryProducer(),
        owner = this,
        defaultArgs = arguments ?: Bundle.EMPTY
    )
})