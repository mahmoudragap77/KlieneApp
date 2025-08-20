plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("com.google.dagger.hilt.android") version "2.57" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.4.2" apply false
}
