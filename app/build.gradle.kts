plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    `kotlin-kapt`
    id ("androidx.navigation.safeargs")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.workwithgithubapi"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(Dependencies.androidCore)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.fragment)
    // CardView
    implementation(Dependencies.cardView)
    //Coil
    implementation(Dependencies.coilKt)
    // Lifecycle
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleRuntimeKtx)
    //Jetpack
    implementation(Dependencies.navigationUIktx)
    implementation(Dependencies.navigationFragmentKtx)
    //Koin
    implementation(Dependencies.koin)
    //Maps
    implementation(Dependencies.androidMapsUtils)
    implementation(Dependencies.playServicesLocation)
    implementation(Dependencies.playServicesMaps)
    //Viewpager
    implementation(Dependencies.viewpager)
    //Splashscreen
    implementation(Dependencies.splashscreen)
    //Coroutines
    implementation(Dependencies.kotlinCoroutines)

    implementation(Dependencies.robolectric)
    //Retrofit
    Dependencies.retrofitLibraries.forEach(::implementation)

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}