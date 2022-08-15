buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.pinterest:ktlint:${Versions.ktlint}")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.21.0")
    }
}

val javaVersion = JavaVersion.VERSION_14

allprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_14.toString()
            javaParameters = true
        }
    }
    val implementation by configurations

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")
    }
}

// detekt
allprojects{
    apply(plugin = "io.gitlab.arturbosch.detekt")

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor (0, "seconds")
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = javaVersion.toString()
    }
    tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
        jvmTarget = javaVersion.toString()
    }
}