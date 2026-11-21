plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.10"
}

android {
    namespace = "com.nandohypesoft.vibecast"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nandohypesoft.vibecast"
        minSdk = 26
        targetSdk = 35
        versionCode = 8
        versionName = "2.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val keyFile1 = file("../vibecast-release.jks")
            val keyFile2 = file("C:/Users/Ferna/Documents/chavevibecast")
            val selectedKey = if (keyFile1.exists()) keyFile1 else if (keyFile2.exists()) keyFile2 else null
            if (selectedKey != null) {
                storeFile = selectedKey
                storePassword = if (selectedKey == keyFile2) "132465" else "password"
                keyAlias = if (selectedKey == keyFile2) "key0" else "wyry-key"
                keyPassword = if (selectedKey == keyFile2) "132465" else "password"
            }
        }
    }

    buildTypes {
        release {
            // Ativa a ofuscação e remoção de código não usado
            isMinifyEnabled = true
            // Remove recursos (imagens, layouts) não utilizados para diminuir o APK
            isShrinkResources = true

            val relSigning = signingConfigs.findByName("release")
            if (relSigning?.storeFile?.exists() == true) {
                signingConfig = relSigning
            }
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            
            ndk.debugSymbolLevel = "FULL"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.common)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.session)
    implementation(libs.ffmpeg.kit)
    implementation(libs.play.billing)
    implementation("com.google.android.gms:play-services-ads:23.6.0")
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}