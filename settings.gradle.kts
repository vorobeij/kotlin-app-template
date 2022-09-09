pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    plugins {
    }
}

plugins {
    id("de.fayard.refreshVersions").version("0.40.2")
}

refreshVersions {
    rejectVersionIf {
        val unstableRegex = Regex("(alpha|beta|rc)\\d*", RegexOption.IGNORE_CASE)
        candidate.value.contains(unstableRegex)
    }
}
rootProject.name = "kotlin-app-template"
include(
    "app"
)
