plugins {
    id("com.android.application") version "7.2.0"
    id("org.jetbrains.kotlin.android") version "1.9.0"
}

android {
    namespace = "cn.xdf.myapplication"

    compileSdk = 34

    defaultConfig {
        applicationId = "cn.xdf.myapplication"
        minSdk = 29
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
        // 1. 开启 Compose 功能
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // 和上面的compileOptions 不一样 。。。
    composeOptions {
        // 2. 指定 Compose 编译器版本 (需要与你的 Kotlin 版本匹配)
        // 你当前使用 kotlin-stdlib:1.9.0，建议使用如下版本：
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // 3. 添加 Compose 核心依赖
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00") // 使用 BOM 管理版本
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3") // 或者使用 material (Material 2)

    // 这里的 activity-compose 版本需要 1.5.0+，你的 context 中 activity 是 1.5.0，最好升级一下
    implementation("androidx.activity:activity-compose:1.8.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


}