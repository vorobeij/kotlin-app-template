package trading.statisics.printer

import trading.statisics.HistogramBar
import java.math.BigDecimal

class CssHistogramPrinter(
    private val delimiter: String = ";"
) : HistogramPrinter {

    override fun print(hist: List<HistogramBar<BigDecimal>>): String {
        return hist
            .map { it.start.toString() + delimiter + it.end.toString() + delimiter + it.count }
            .joinToString("\n")
    }
}
