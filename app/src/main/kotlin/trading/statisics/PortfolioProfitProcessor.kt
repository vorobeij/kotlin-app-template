package trading.statisics

import org.nield.kotlinstatistics.average
import trading.report.LineChartPoint
import java.time.Duration

class PortfolioProfitProcessor {

    fun values(tickers: List<TickerCandles>): List<LineChartPoint> {
        val minSize = tickers.minOf { it.candles.size }
        val lists = tickers.map { it.copy(candles = it.candles.subList(it.candles.size - minSize, it.candles.size)) }
        if (lists.any { it.candles.size != minSize }) error("incorrect sizes!")

        val startTime = lists[0].candles[0].time
        val profits = mutableListOf<LineChartPoint>()
        for (i in 0 until minSize) {
            val p = lists.map { it.candles[i].closePrice / it.candles[0].closePrice }.average()
            profits.add(
                LineChartPoint(
                    x = Duration.between(startTime, lists[0].candles[i].time).toDays().toInt(),
                    y = p
                )
            )
        }
        return profits
    }
}