package trading.statisics

import java.math.BigDecimal
import java.math.MathContext
import kotlin.math.ceil

class HistogramBuilder(
    private val values: List<BigDecimal>
) {

    private val mc = MathContext(2)

    fun buildHistogram(): List<HistogramBar<BigDecimal>> {

        if (values.isEmpty()) return emptyList()

        val counts = values.toCountMap()
        val max = values.maxOrNull()!!
        val min = values.minOrNull()!!
        val step = (max - min).toDouble() / counts.size
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
        val step: BigDecimal = (max - min) / BigDecimal(numberOfIntervals, mc)
        (0 until numberOfIntervals).map {
            val start = min + step * BigDecimal(it, mc)
            val end = start + step
            bars.add(
                HistogramBar(
                    start.norm(),
                    end.norm(),
                    values.filter { v -> v >= start && v < end }.count()
                )
            )
        }
        if (bars.any { it.count == 0 }) {
            val newNumberOfIntervals = ceil(numberOfIntervals.toDouble() * 0.9).toInt()
            return if (newNumberOfIntervals == numberOfIntervals) bars else buildHist(values, newNumberOfIntervals)
        }
        return bars
    }

    private fun BigDecimal.norm() = BigDecimal(this.toDouble(), mc)
}
