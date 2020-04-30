import deps.Injection
import deps.Work

plugins {
    id("com.android.library")
    id("com.novoda.bintray-release")
    id("project-module-plugin")
}

publish {
    userOrg = "matiasdelbel"
    repoName = "maven"

    groupId = "com.delbel.dagger"
    artifactId = "dagger-work"
    publishVersion = "0.1.0"

    desc = "Android library that integrate JetPack Worker and Dagger"
    website = "https://github.com/matiasdelbel/dagger-support"
}


dependencies {
    implementation(Injection.dagger)

    kapt(Injection.daggerCompiler)

    implementation(Work.runtime)
}