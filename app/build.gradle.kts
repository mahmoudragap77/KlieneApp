plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.klieneapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.klieneapp"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



    //loading button

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //circular image
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //viewpager2 indicatior
    implementation ("androidx.viewpager2:viewpager2:1.1.0")

    //stepView
    implementation("com.github.shuhart:stepview:1.5.1")
    //Android Ktx
    implementation ("androidx.navigation:navigation-fragment-ktx:2.9.3")

    //Dagger hilt
    implementation ("com.google.dagger:hilt-android:2.38.1")
     kapt ("com.google.dagger:hilt-compiler:2.38.1")
}