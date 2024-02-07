plugins {
    id("com.android.application")
    id("org.openapi.generator")
}

android {
    namespace = "com.example.vendor_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vendor_app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.named("preBuild") {
        dependsOn("openApiGenerate")
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/java", "build/FDS_Api/src/main/java")
    }

    useLibrary("org.apache.http.legacy")

    packaging {
        resources.excludes.add("META-INF/*")
    }

}

openApiGenerate {
    generatorName.set("android")
    inputSpec.set("$rootDir/app/api.yaml")
    outputDir.set("$buildDir/FDS_Api")
    apiPackage.set("com.openapi.deliveryApp.api")
    invokerPackage.set("com.openapi.deliveryApp.invoker")
    modelPackage.set("com.openapi.deliveryApp.model")
    typeMappings.set(mapOf("number" to "Float"))
    configOptions.set(mapOf("dateLibrary" to "java8"))
}

//tasks.build {
//    dependsOn("assemble", "openApiGenerate")
//}

dependencies {
    implementation("com.google.android.material:material:1.11.0")
    implementation("com.airbnb.android:lottie:4.0.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.compose.animation:animation-graphics-android:1.6.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("androidx.compose.material:material:1.6.0")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.apache.httpcomponents:httpclient-android:4.3.5.1")
    implementation ("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("org.apache.httpcomponents:httpclient-android:4.3.5.1")
    implementation(group = "org.apache.httpcomponents", name = "httpclient-android", version = "4.3.5.1")
    implementation(group = "org.apache.httpcomponents", name = "httpmime", version = "4.3") {
        exclude(group = "org.apache.httpcomponents", module = "httpclient")
    }
    implementation("io.reactivex.rxjava2:rxandroid:2.0.2")
    implementation("io.reactivex.rxjava2:rxjava:2.1.13")
}