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
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //val composeBomVersion = "2023.08.00"
    val composeBomVersion = "2024.02.00"
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    //val activityComposeVersion = "1.8.2"
    val activityComposeVersion = "1.9.0-alpha03"
    implementation("androidx.activity:activity-compose:$activityComposeVersion")

    //val navigationComposeVersion = "2.7.7"
    val navigationComposeVersion = "2.8.0-alpha02"
    implementation("androidx.navigation:navigation-compose:$navigationComposeVersion")
    implementation("androidx.navigation:navigation-runtime-ktx:$navigationComposeVersion")

    //val wearComposeVersion = "1.2.1"             // 長押しが効く
    //val wearComposeVersion = "1.3.0-alpha01"     // 起動しない alpha01 ～ alpha05
    //val wearComposeVersion = "1.3.0-alpha06"     // 長押しが効かない
    //val wearComposeVersion = "1.3.0"             // 長押しが効かない
    //val wearComposeVersion = "1.4.0-alpha02"     // 長押しが効かない
    val wearComposeVersion = "1.4.0-alpha03"     // 長押しが効かない
    implementation("androidx.wear.compose:compose-material:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-foundation:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-navigation:$wearComposeVersion")

    // for ongoing activity API
    //val wearOnGoingVersion = "1.0.0"
    val wearOnGoingVersion = "1.1.0-alpha01"
    implementation("androidx.wear:wear-ongoing:$wearOnGoingVersion")

    //implementation("androidx.core:core-ktx:2.2.0")
    //implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.core:core-ktx:1.13.0-alpha05")

    // Horologist
    implementation("com.google.android.horologist:horologist-compose-material:0.6.0")
}
