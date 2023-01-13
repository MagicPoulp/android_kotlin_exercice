plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    // https://plugins.gradle.org/plugin/org.jetbrains.compose
    id("org.jetbrains.compose") version "1.3.0-rc02"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
