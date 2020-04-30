plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {

    plugins {
        val projectModulePlugin = "project-module-plugin"

        register(projectModulePlugin) {
            id = projectModulePlugin
            implementationClass = "ProjectModulePlugin"
        }
    }
}

repositories {
    google()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())

    implementation("com.android.tools.build:gradle:3.6.3")
    implementation(kotlin("gradle-plugin", "1.3.61"))

    implementation("org.jacoco:org.jacoco.core:0.8.5")
    implementation("com.novoda:bintray-release:0.9.2")
}

sourceSets {
    val main by getting
    main.java.srcDirs("src/main/kotlinX")
}