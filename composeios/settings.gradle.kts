pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        //maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
        //maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        //classpath("com.android.tools.build:gradle:7.4.0")
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.22")
    }
}

// https://markonovakovic.medium.com/jetpack-compose-for-ios-getting-started-step-by-step-e7be6f52edd4
// https://gist.github.com/marenovakovic/e6c3b051922cdcc167bfe07dd4793872
// https://gist.github.com/marenovakovic/3fd64f4c57c4f32a4b337367ec430748
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

rootProject.name = "compose-ios"
include(":androidApp")
include(":shared")