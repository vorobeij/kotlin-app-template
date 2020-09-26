plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
}

dependencies {

    implementation(kotlin("stdlib"))

    implementation(Libs.annotation)
    implementation(Libs.coroutines_core)
    implementation(Libs.kotlin)
    implementation(Libs.okhttp)
    implementation(Libs.okhttp3_logging_interceptor)
    implementation(Libs.okhttp_url_connection)
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_kotlin_serialisation_converter)
    implementation(group = "com.google.guava", name = "guava", version = "29.0-jre")

    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.kotlintest)
}
