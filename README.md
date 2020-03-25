# Dagger Support

## Rx
Dagger module that provides schedulers (computation, IO and main).
You can annotate your `Scheduler`s, and then dagger will inject it for you.

### How to use it?
Add the module in your component:
```
@Component(modules = [DaggerRxModule::class])
interface MainComponent
```

Then, you can annotate the scheduler that you want to inject
```
class Repository @Inject constructor(@ComputationScheduler private val scheduler: Scheduler)
```

## View Model
Dagger module that provides a view model factory (`ViewModelFactory`).
You can inject view models with the `ViewModelKey` annotation.

### How to use it?
Bind the created view model
```
@Module
internal interface ScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindListingViewModel(viewModel: MainViewModel): ViewModel
}
```

Add the modules in your component:
```
@Component(modules = [DaggerViewModelFactoryModule::class, ScreenModule::class])
interface MainComponent
```

Inject the view model into your view:
```
class MainScreen : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = viewModelFactory.create(this, MainViewModel::class.java)
    }
```

## Work
Dagger module that provides a worker factory (`MainWorkerFactory`).
You can inject factories that create workers with the `WorkerKey` annotation.

### How to use it?
Create a factory inside the worker:
```
class AppWorker(
    appContext: Context,
    params: WorkerParameters,
    private val dependency: Dependency
) : Worker(appContext, params) {

    override fun doWork(): Result = TODO("not implemented")

    class Factory @Inject constructor(
        private val dependencyProvider: Provider<Dependency>
    ) : ListenableWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker =
            AppWorker(appContext, params, dependencyProvider.get())
    }
}
```

Bind the created worker,
```
@Module
internal interface WorkerBindingModule {

    @Binds
    @IntoMap
    @WorkerKey(AppWorker::class)
    fun bindAppWorker(factory: AppWorker.Factory): ListenableWorkerFactory
}
```

Add the module in your component:
```
@Component(modules = [WorkerModule::class, WorkerBindingModule::class])
interface MainComponent
```

Finally, set the work manager custom factory
```
class MainApplication : Application() {

    private val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder().application(application = this).build()
    }

    @Inject
    lateinit var factory: WorkerFactory

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
        initializeWorkManager(factory)
    }

    private fun injectDependencies() = mainComponent.inject(application = this)
}
```