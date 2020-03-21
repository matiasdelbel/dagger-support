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

### Dependency
```
 implementation "com.delbel.dagger:dagger-rx:0.0.3"
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

### Dependency
```
 implementation "com.delbel.dagger:dagger-work:0.0.2"
```