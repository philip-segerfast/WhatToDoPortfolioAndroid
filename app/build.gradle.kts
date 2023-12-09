plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "segerfast.philip.whattodo"
    compileSdk = 34

    defaultConfig {
        applicationId = "segerfast.philip.whattodo"
        minSdk = 28
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()

        // Enable Coroutines and Flow APIs
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    testImplementation("junit:junit:4.13.2")


    apply hilt@ {
        ksp("com.google.dagger:dagger-compiler:2.48.1")
        ksp("com.google.dagger:hilt-android-compiler:2.48.1")

        implementation("com.google.dagger:hilt-android:2.48.1")
        implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    }

    apply room@ {
        val roomVersion = "2.6.1"

        implementation("androidx.room:room-runtime:$roomVersion")
        annotationProcessor("androidx.room:room-compiler:$roomVersion")

        // To use Kotlin Symbol Processing (KSP)
        ksp("androidx.room:room-compiler:$roomVersion")

        // optional - Kotlin Extensions and Coroutines support for Room
        implementation("androidx.room:room-ktx:$roomVersion")

        // optional - Test helpers
        testImplementation("androidx.room:room-testing:$roomVersion")
    }

    apply androidx@ {

        implementation("androidx.core:core-ktx:1.12.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

        apply navigation@ {
            val nav_version = "2.7.5"

            implementation("androidx.navigation:navigation-compose:$nav_version")
        }

        apply workManager@ {
            val work_version = "2.9.0"

            // Kotlin + coroutines
            implementation("androidx.work:work-runtime-ktx:$work_version")
        }

        apply compose@ {
            implementation(platform("androidx.compose:compose-bom:2023.10.01"))
            implementation("androidx.activity:activity-compose:1.8.1")
            implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
            implementation("androidx.compose.ui:ui")
            implementation("androidx.compose.ui:ui-graphics")
            implementation("androidx.compose.ui:ui-tooling-preview")
            implementation("androidx.compose.material3:material3")

            apply test@ {
                // Test
                androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
                androidTestImplementation("androidx.compose.ui:ui-test-junit4")
            }

            apply debug@ {
                // Debug
                debugImplementation("androidx.compose.ui:ui-tooling")
                debugImplementation("androidx.compose.ui:ui-test-manifest")
            }
        }

        apply test@ {
            androidTestImplementation("androidx.test.ext:junit:1.1.5")
            androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        }
    }

    apply {
        implementation("com.jakewharton.timber:timber:5.0.1")
    }
}