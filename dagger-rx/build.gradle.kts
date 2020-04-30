import deps.Injection
import deps.Rx

plugins {
    id("com.android.library")
    id("com.novoda.bintray-release")
    id("project-module-plugin")
}

publish {
    userOrg = "matiasdelbel"
    repoName = "maven"

    groupId = "com.delbel.dagger"
    artifactId = "dagger-rx"
    publishVersion = "0.0.3"

    desc = "Android library that integrate RxJava and Dagger"
    website = "https://github.com/matiasdelbel/dagger-support"
}


dependencies {
    implementation(Injection.dagger)

    kapt(Injection.daggerCompiler)

    implementation(Rx.rxJava)
    implementation(Rx.rxAndroid)
}