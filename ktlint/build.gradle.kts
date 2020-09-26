import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"
plugins {
    id("java-library")
    id("kotlin")
}
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(kotlin("gradle-plugin", Versions.kotlin))
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
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
    jcenter()
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