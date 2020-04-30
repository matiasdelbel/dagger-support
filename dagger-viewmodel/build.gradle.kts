import deps.Injection
import deps.ViewModel

plugins {
            id("com.android.library")
            id("com.novoda.bintray-release")
            id("project-module-plugin")
        }


publish {
    userOrg = "matiasdelbel"
    repoName = "maven"

    groupId = "com.delbel.dagger"
    artifactId = "dagger-viewmodel"
    publishVersion = "0.1.0"

    desc = "Android library that integrate JetPack View Model and Dagger"
    website = "https://github.com/matiasdelbel/dagger-support"
}

dependencies {
    implementation(Injection.dagger)
    kapt(Injection.daggerCompiler)

    implementation(ViewModel.fragment)
    implementation(ViewModel.viewModel)
    implementation(ViewModel.lifecycleExt)
}