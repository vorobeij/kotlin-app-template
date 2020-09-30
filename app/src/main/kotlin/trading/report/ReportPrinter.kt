package trading.report

import trading.infrastructure.ResourcesUtils
import java.io.File

class ReportPrinter(
    private val rootDir: String
) {

    fun print(html: String) {
        val folderSources = ResourcesUtils.getFile("html-report")
        val folderOut = File(rootDir, folderSources.name)
        folderSources.copyRecursively(folderOut, overwrite = true)
        File(folderOut, "index.html").writeText(html)
    }
}
