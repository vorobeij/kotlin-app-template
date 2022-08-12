import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = Versions.JAVA.toString()
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = Versions.JAVA.toString()

plugins {
    id("java-library")
    id("kotlin")
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(kotlin("gradle-plugin", Versions.kotlin))
}

java {
    sourceCompatibility = Versions.JAVA
    targetCompatibility = Versions.JAVA
}
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}
repositories {
    google()
    maven(url = "https://jitpack.io")
}
dependencies {
    implementation("com.pinterest.ktlint:ktlint-core:${Versions.ktlint}")

    testImplementation("com.pinterest.ktlint:ktlint-core:${Versions.ktlint}")
    testImplementation("com.pinterest.ktlint:ktlint-test:${Versions.ktlint}")
    testImplementation("androidx.test.ext:junit:${Versions.Test.junit}")
    testImplementation("org.amshove.kluent:kluent:${Versions.Test.kluent}")
    testImplementation("org.assertj:assertj-core:${Versions.Test.assertj}")
}