
buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.40.1")

        val nav_version = "2.5.0"
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
