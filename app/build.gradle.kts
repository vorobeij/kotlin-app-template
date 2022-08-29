plugins {
    kotlin("jvm")
}

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