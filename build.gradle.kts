buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.pinterest:ktlint:0.46.1")
    }
}

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
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")
    }
}
