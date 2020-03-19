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