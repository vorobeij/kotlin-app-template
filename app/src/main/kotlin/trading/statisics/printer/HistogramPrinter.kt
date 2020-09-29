package trading.statisics.printer

import trading.statisics.HistogramBar
import java.math.BigDecimal

interface HistogramPrinter {

    fun print(hist: List<HistogramBar<BigDecimal>>): String
}
