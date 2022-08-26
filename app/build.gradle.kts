plugins {
    kotlin("jvm")
}

apply(from = "${rootDir.absolutePath}/gradle/detekt/detekt-config.gradle")

dependencies {
    implementation(kotlin("stdlib"))
}

// https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin/wiki/Customizing-plugin-behavior
dependencyAnalysis {
    issues {
        ignoreKtx(true)
        onAny {
            severity("fail")
            exclude(
                "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
            )
        }
    }
}