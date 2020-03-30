# View Model
`dagger-viewmodel` is a light weight library that helps to inject dependencies to a viewmodel
using dagger.

When you inject dependencies to a view model you can have two situation:
1- All the dependencies are from your Dagger graph.
2- You need some dinamic dependencies, for example arguments obtained from a fragment.

## How to use the library?

### Scenario 1: All the VM dependencies can be otained form Dagger
`dagger-viewmodel` provides a view model factory (`ViewModelFactory`).
You can inject view models with the `ViewModelKey` annotation.

First, bind the created view model, to the ViewModelFactory:
```
@Module(modules = [ViewModelFactoryModule::class])
internal interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindListingViewModel(viewModel: MainViewModel): ViewModel
}
```

And then, inject the view model into your view:
```
class MainScreen : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }
```

### Scenario 2: You need dependencies from Fragment arguments
In this case, you need to create a factory class for your viewmodel, is recomend use
square assistant library for that:
```
class DetailViewModel @AssistedInject constructor(
    private val repository: TextRepository,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<DetailViewModel>
```

Then, the genreated module should be loaded to the dagger module:
```
@AssistedModule
@Module(includes = [AssistedInject_AssistedViewModelModule::class])
interface AssistedViewModelModule
```

Finally, we inject the view model into your view
```
class DetailScreen : Fragment(R.layout.screen_detail) {

   @Inject
    internal lateinit var factory: DetailViewModel.Factory
    private val viewModel: DetailViewModel by viewModels { factory }
```