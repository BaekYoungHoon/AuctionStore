

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
}

detekt {
    autoCorrect = true
}
android {
    namespace = "com.hoon.AuctionStore"
    compileSdk = 34

    buildFeatures {
        // Enables Jetpack Compose for this module
        compose = true
    }

    defaultConfig {
        applicationId = "com.hoon.AuctionStore"
        minSdk = 33
        targetSdk = 33
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.android.material:material:1.1.0")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")

    implementation ("androidx.compose.ui:ui:1.0.0-beta07")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui-tooling:1.0.0-beta07")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation ("androidx.compose.foundation:foundation:1.0.0-beta07")
    // Material Design
    implementation ("androidx.compose.material:material:1.0.0-beta07")
    // Material design icons
    implementation ("androidx.compose.material:material-icons-core:1.0.0-beta07")
    implementation ("androidx.compose.material:material-icons-extended:1.0.0-beta07")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.3.0-alpha08")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha05")
    // Integration with observables
    implementation ("androidx.compose.runtime:runtime-livedata:1.0.0-beta07")
    implementation ("androidx.compose.runtime:runtime-rxjava2:1.0.0-beta07")

    // UI Tests
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.0.0-beta07")
}