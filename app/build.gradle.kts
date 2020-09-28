plugins {
    kotlin("jvm")
}

apply(from = "../defaults.gradle")

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(Modules.tinkoffApi))
    implementation(project(Modules.tinkoffApiSdk))

    implementation(Libs.reactive_streams)
    implementation(Libs.reactive_streams_examples)
    implementation(Libs.jackson_annotations)
    implementation(Libs.jackson_core)
    implementation(Libs.jackson_databind)
    implementation(Libs.jackson_datatype_jdk_8)
    implementation(Libs.jackson_datatype_jsr310)
}

apply(from = "../ktlint.gradle.kts")
