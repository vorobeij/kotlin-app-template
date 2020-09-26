plugins {
    java
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(Modules.tinkoffApi))

    implementation(Libs.jackson_core)
    implementation(Libs.jackson_annotations)
    implementation(Libs.jackson_databind)
    implementation(Libs.jackson_datatype_jdk_8)
    implementation(Libs.jackson_datatype_jsr310)
    implementation(Libs.jetbrains_annotations)
    implementation(Libs.reactive_streams)
    implementation(Libs.okhttp)
    testImplementation(Libs.jupiter)
    testImplementation(Libs.mockitoCore)
}
