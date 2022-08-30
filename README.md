# Template kotlin project

To check build and test project:

```shell
./gradlew runChecks
```

## Features:

1. Diktat custom [ruleset](./diktat-analysis.yml)
2. `buildSrc` with common libraries
3. `kts` build files
4. pre-configured `.gitignore` file

## Plugins

1. [Refresh versions](https://github.com/jmfayard/refreshVersions)
1. [Dependencies health check](https://github.com/autonomousapps/dependency-analysis-android-gradle-plugin)

```shell
./gradlew buildHealth
```

For each module set fail on excessive dependencies:

```kotlin
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
```