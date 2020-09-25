plugins {
    kotlin("jvm")
}

dependencies {

    implementation(kotlin("stdlib"))

    implementation(Libs.moshi_kotlin)
    implementation(Libs.moshi_adapters)
    implementation(Libs.threetenbp)
    implementation(Libs.okhttp)
    testImplementation(Libs.kotlintest)
}
