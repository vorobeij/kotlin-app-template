plugins {
    kotlin("jvm")
}

apply(from = "${rootDir.absolutePath}/gradle/defaults.gradle")
apply(from = "${rootDir.absolutePath}/gradle/detekt/detekt-config.gradle")

dependencies {
    implementation(kotlin("stdlib"))

    implementation(Libs.koin_core)
    testImplementation(Libs.koin_test)
}
