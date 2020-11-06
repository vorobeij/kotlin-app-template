package trading.infrastructure

import trading.App
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.LogManager
import java.util.logging.Logger

val logger: Logger by lazy {
    initLogger()
}

private fun initLogger(): Logger {
    val logManager = LogManager.getLogManager()
    val classLoader = App::class.java.classLoader
    classLoader.getResourceAsStream("logging.properties").use { input ->
        if (input == null) {
            throw FileNotFoundException()
        }
        Files.createDirectories(Paths.get("./logs"))
        logManager.readConfiguration(input)
    }
    return Logger.getLogger(App::class.java.name)
}
