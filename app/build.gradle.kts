import deps.Injection
import deps.Presentation
import deps.Rx
import deps.Work
import deps.ViewModel
import deps.App

plugins {
    id("com.android.application")
    id("project-module-plugin")
}

android {

    defaultConfig.applicationId = "com.delbel.dagger.testapp"

    viewBinding { isEnabled = true }
}

dependencies {
    implementation(project(":dagger-rx"))
    implementation(project(":dagger-work"))
    implementation(project(":dagger-viewmodel"))

    implementation(Injection.dagger)
    implementation(Injection.daggerSupport)
    implementation(Injection.assistedInjection)

    kapt(Injection.daggerProcessor)
    kapt(Injection.daggerCompiler)
    kapt(Injection.assistedInjectionCompiler)

    implementation(Presentation.appCompat)

    implementation(Rx.rxJava)
    implementation(Rx.rxAndroid)

    implementation(ViewModel.viewModel)
    implementation(ViewModel.fragment)
    implementation(ViewModel.lifecycleExt)

    implementation(Work.runtime)

    implementation (App.reactiveStreams)
}