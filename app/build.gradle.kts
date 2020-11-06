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
    implementation(Libs.koin_core)
    implementation(Libs.koin_core_ext)
    implementation(Libs.html)
    implementation(Libs.statistics)

    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:0.13.0")
    implementation("org.jetbrains:kotlin-numpy:0.1.5")

    testImplementation(Libs.koin_test)
}

apply(from = "../ktlint.gradle.kts")
