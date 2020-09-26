val ktlint: Configuration by configurations.creating
val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
dependencies {
    ktlint("com.pinterest:ktlint:${Versions.ktlint}")

    ktlint(project(":ktlint"))
}
val ktlintFormat by tasks.creating(JavaExec::class) {
    inputs.files(inputFiles)
    outputs.dir(outputDir)
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}
tasks {
    getTasksByName("preBuild", false).forEach {
        it.dependsOn(ktlintFormat)
    }
}
