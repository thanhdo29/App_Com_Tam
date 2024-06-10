plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)


    id("kotlin-android")
    id("kotlin-kapt")

}

apply(plugin = "kotlin-parcelize")

android {
    namespace = "com.example.app_com_tam"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.app_com_tam"
        minSdk = 24
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
    //icon
    implementation("androidx.compose.material:material-icons-core:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.0.1")
    //asyc image

    implementation("io.coil-kt:coil-gif:2.6.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    implementation ("androidx.room:room-ktx:2.6.1")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.26.3-beta")

    implementation("androidx.compose.material:material-icons-core:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.0.1")


    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")

    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
}