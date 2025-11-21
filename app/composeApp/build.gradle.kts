import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "2.2.21"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("com.google.android.gms:play-services-location:21.3.0")


        }
        commonMain.dependencies {
            //navegacion
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.1")

            //fecha y hora
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

            //iconos
            implementation(compose.materialIconsExtended)
            //tamanyo pantalla
            implementation("org.jetbrains.compose.material3.adaptive:adaptive:1.1.2")
            //json
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
            //carga de imagenes
            implementation("io.coil-kt.coil3:coil-compose:3.3.0")
            implementation("org.springframework.data:spring-data-jdbc:4.0.0")
            implementation("com.mysql:mysql-connector-j:9.5.0")

           implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation("org.apache.derby:derby:10.16.1.1")
            implementation("com.mysql:mysql-connector-j:9.5.0")
        }
    }
}

android {
    namespace = "ies.sequeros.com.dam.pmdm"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "ies.sequeros.com.dam.pmdm"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "ies.sequeros.com.dam.pmdm.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ies.sequeros.com.dam.pmdm"
            packageVersion = "1.0.0"
        }
    }
}
