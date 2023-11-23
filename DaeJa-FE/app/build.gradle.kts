plugins {
    id("com.android.application")
}

android {
    namespace = "com.app.daeja"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.daeja"
        minSdk = 26
        targetSdk = 33
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

    buildFeatures{
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
        exclude("mozilla/public-suffix-list.txt")
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(files("../libs/com.skt.Tmap_1.75.jar"))
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("com.github.bumptech.glide:glide:4.13.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.sothree.slidinguppanel:library:3.4.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //implementation("com.squareup.retrofit:converter-gson:2.0.0-beta2")


    //androidTestImplementation("androidx.test.ext:junit:1.1.5")

    //json
//    implementation("com.google.code.gson:gson:2.10")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    //testImplementation("io.rest-assured:rest-assured:5.1.1")
    implementation("io.rest-assured:rest-assured:5.1.1")
    //implementation("org.apache.httpcomponents:httpclient:4.5.13")
    //implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    //implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
  //  implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
//    implementation("com.google.code.gson:gson:2.10")



    //implementation("com.github.majorkik:SparKLineLayout:1.0.1")
    //implementation("com.github. ome450901:SimpleRatingBar: 1.5.1")
    //implementation("com.google.android material:material:1.4.0")
    //implementation("com.google.android.material:material:1.11.0-beta01")

}