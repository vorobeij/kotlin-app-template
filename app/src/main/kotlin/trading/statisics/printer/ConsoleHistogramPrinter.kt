package trading.statisics.printer

import trading.statisics.HistogramBar
import java.math.BigDecimal

class ConsoleHistogramPrinter : HistogramPrinter {

    override fun print(hist: List<HistogramBar<BigDecimal>>): String {
        val max = hist.maxByOrNull { it.count }!!.count
        val total = hist.sumBy { it.count }
        val divider = max / 30.0
        val maxStartSize = hist.maxByOrNull { it.start.toString().length }!!.start.toString().length
        return "%${maxStartSize}s".format(hist[0].start.toString()) + "\n" + hist.map {
            "%${maxStartSize}s".format(it.end.toString()) + " " + "=".repeat((it.count / divider).toInt()) + " " + it.count + " %.2f".format(1f * it.count / total)
        }.joinToString("\n")
    }
}
