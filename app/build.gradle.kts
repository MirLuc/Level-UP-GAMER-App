plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

// Agregar esto para habilitar KAPT
    kotlin("kapt")

}

android {
    namespace = "com.example.login001v"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.login001v"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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


    // Dependencia para la navegación con Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

// Íconos (core opcional) y EXTENDIDOS (¡este es el clave!)
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")

    // Dependencias Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")


    // -----------------------------------------------------------------
    // REQUISITO 1: DEPENDENCIAS DE RED (RETROFIT)
    // -----------------------------------------------------------------

    // Retrofit y Gson Converter para consumo de API
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Corrutinas para trabajo asincrónico (esencial para Retrofit y ViewModel)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("io.coil-kt:coil-compose:2.6.0")


    // -----------------------------------------------------------------
    // REQUISITO 2: DEPENDENCIAS DE PRUEBA AVANZADAS
    // -----------------------------------------------------------------

    // Kotest (para escribir tests con un DSL más expresivo)
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")

    // MockK (para crear objetos simulados de Repositorios y Servicios)
    testImplementation("io.mockk:mockk:1.13.10")

    // Coroutines Test (esencial para probar el ViewModel y el Repositorio)
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")

    // AndroidX Test (para pruebas de arquitectura, necesario para corrutinas en tests)
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // JUnit 5 Engine (para que Gradle pueda ejecutar los tests de Kotest)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// Configuración necesaria para usar JUnit 5 (Kotest)
tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "failed", "skipped")
    }
}