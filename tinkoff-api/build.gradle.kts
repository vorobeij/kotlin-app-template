plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version Versions.kotlin
}

dependencies {

    implementation(kotlin("stdlib"))

    implementation(Libs.coroutines_core)
    implementation(Libs.kotlin)
    implementation(Libs.okhttp)
    implementation(Libs.okhttp3_logging_interceptor)
    implementation(Libs.okhttp_url_connection)
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_kotlin_serialisation_converter)
    implementation(group = "com.google.guava", name = "guava", version = "29.0-jre")

    implementation(Libs.jackson_core)
    implementation(Libs.jackson_annotations)
    implementation(Libs.jackson_databind)
    implementation(Libs.jackson_datatype_jdk_8)
    implementation(Libs.jackson_datatype_jsr310)
    implementation(Libs.jetbrains_annotations)
    implementation(Libs.reactive_streams)

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation(Libs.mockitoCore)
    testImplementation(Libs.junit)
    testImplementation(Libs.truth)
    testImplementation(Libs.kotlintest)
}
