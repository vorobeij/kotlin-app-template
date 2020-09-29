package trading.statisics

import java.math.BigDecimal
import kotlin.math.ceil

class HistogramBuilder(
    private val values: List<BigDecimal>
) {

    fun buildHistogram(): List<HistogramBar<BigDecimal>> {

        if (values.isEmpty()) return emptyList()

        val counts = values.toCountMap()
        val max = values.maxOrNull()!!
        val min = values.minOrNull()!!
        val step = ceil(((max - min).toDouble() / counts.size)).toInt()
        val intervals = ceil((max - min).toDouble() / step).toInt()
        return buildHist(values, if (intervals > 0) intervals else 1)
    }

    private fun buildHist(
        values: List<BigDecimal>,
        numberOfIntervals: Int
    ): List<HistogramBar<BigDecimal>> {

        val bars = mutableListOf<HistogramBar<BigDecimal>>()
        val min = values.minOrNull()!!
        var max = values.maxOrNull()!!
        if (min == max) max = min + BigDecimal(1)
        val step = (max - min) / numberOfIntervals.toBigDecimal()
        (0 until numberOfIntervals).map {
            val start = min + step * it.toBigDecimal()
            val end = start + step
            bars.add(
                HistogramBar(
                    start, end,
                    values.filter { v -> v >= start && v < end }.count()
                )
            )
        }
        if (bars.any { it.count == 0 }) return buildHist(values, ceil(numberOfIntervals.toDouble() / 2).toInt())
        return bars
    }

    fun printConsole(): String {
        val hist = buildHistogram()
        val max = hist.maxByOrNull { it.count }!!.count
        val divider = max / 30.0
        val maxStartSize = hist.maxByOrNull { it.start.toString().length }!!.start.toString().length
        return hist.map {
            "%${maxStartSize}s".format(it.start.toString()) + " " + "=".repeat((it.count / divider).toInt()) + " " + it.count
        }.joinToString("\n")
    }

    fun css(delimiter: String = ";"): String {

        val hist = buildHistogram()
        return hist
            .map { it.start.toString() + delimiter + it.end.toString() + delimiter + it.count }
            .joinToString("\n")
    }
}
