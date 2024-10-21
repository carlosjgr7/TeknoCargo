apply(from = "module_structure.gradle.kts")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.gradle)
    alias(libs.plugins.jetbrainsKotlinSerialization)
    alias(libs.plugins.kotlin.parcelize)
    kotlin("kapt")

}

android {
    namespace = "com.delivery.tecnokargo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.delivery.tecnokargo"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.json)
    implementation(libs.nav.compose)
    implementation(libs.material)
    implementation(libs.material.icons)
    //conmstrainLayout
    implementation(libs.android.constraint.layout)
    //lottie
    implementation(libs.lottie)
    //hilt
    implementation(libs.hilt.core)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    //Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    //dataStore
    implementation(libs.data.store)
    //okhttp
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)
    //chucker
    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

