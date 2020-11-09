plugins {
    kotlin("jvm")
}

apply(from = "../defaults.gradle")

dependencies {
    implementation(kotlin("stdlib"))

    implementation(Libs.koin_core)
    implementation(Libs.koin_core_ext)

    testImplementation(Libs.koin_test)
}

apply(from = "../ktlint.gradle.kts")
