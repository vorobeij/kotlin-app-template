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
}
