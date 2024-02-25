plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "net.osdn.ja.gokigen.wearos.timerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "net.osdn.ja.gokigen.wearos.timerapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 100002
        versionName = "1.0.2"
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
        jvmTarget = "17"
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
    // Standard dependencies
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Wear Compose
    val wearComposeVersion = "1.4.0-alpha03"       // "1.2.1"
    implementation("androidx.wear.compose:compose-material:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-foundation:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-navigation:$wearComposeVersion")

    // for ongoing activity API
    val wearOnGoingVersion = "1.1.0-alpha01"  // "1.0.0"
    implementation("androidx.wear:wear-ongoing:$wearOnGoingVersion")

    // Horologist
    val wearHorologistVersion = "0.6.0"
    implementation("com.google.android.horologist:horologist-compose-material:$wearHorologistVersion")
}
