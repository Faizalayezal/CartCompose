plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.navigationDestination)
    id("kotlin-parcelize")
    id("kotlin-kapt")


}

android {
    namespace = "com.example.faizal_task_empyreal"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.faizal_task_empyreal"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.debug)

    coreLibraryDesugaring(libs.desugar.jdk.libs)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

   //Di
    implementation(libs.bundles.koin)

    implementation(libs.destinations)
    ksp(libs.destinationsKsp)

    //compose image loading
    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation("androidx.compose.animation:animation:1.9.4")
    implementation("androidx.compose.material3:material3:1.4.0-alpha15")
    implementation("androidx.compose.animation:animation:1.8.2")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")
    //essentials
    implementation("androidx.compose.material3:material3-android:1.3.2")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.9.4")



}