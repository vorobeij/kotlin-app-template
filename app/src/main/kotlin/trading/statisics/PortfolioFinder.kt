package trading.statisics

import org.nield.kotlinstatistics.Descriptives
import org.nield.kotlinstatistics.descriptiveStatistics

class PortfolioFinder(
    val investmentPeriodDays: Long,
    private val strategy: PFStrategy
) {

    private val processor = DailyInvestmentsPortfolioProfitProcessor(investmentPeriodDays)

    fun findBestSet(candles: List<TickerCandles>): List<TickerCandles> {
        val goodStocks = mutableListOf<TickerCandles>()
        // 1 get stats for a given set:
        val givenSetStats = processor.values(candles.map { it.candles }).descriptiveStatistics
        val removedStocks = mutableListOf<TickerCandles>()

        while (removedStocks.size != candles.size) {
            val newCandles = candles.toMutableList()
            val toRemove = newCandles.first { it !in removedStocks }
            newCandles.remove(toRemove)
            removedStocks.add(toRemove)

            // get metrics and compare with given set numbers
            val newStats = processor.values(newCandles.map { it.candles }).descriptiveStatistics
            if (strategy.isGood(givenSetStats, newStats)) {
                goodStocks.add(toRemove)
            }

            newCandles.add(toRemove)
        }
        return goodStocks
    }
}

interface PFStrategy {

    fun isGood(
        givenStats: Descriptives,
        newStats: Descriptives
    ): Boolean
}

class PFStrategy1 : PFStrategy {

    override fun isGood(
        givenStats: Descriptives,
        newStats: Descriptives
    ): Boolean {
        return newStats.percentile(5.0) < givenStats.percentile(5.0)
    }
}

class PFStrategy2 : PFStrategy {

    override fun isGood(
        givenStats: Descriptives,
        newStats: Descriptives
    ): Boolean {
        return newStats.standardDeviation > givenStats.standardDeviation
        /*newStats.percentile(5.0) < givenStats.percentile(5.0) ||
            newStats.standardDeviation < givenStats.standardDeviation*/
    }
}

class PFStrategy3 : PFStrategy {

    override fun isGood(
        givenStats: Descriptives,
        newStats: Descriptives
    ): Boolean {
        return newStats.percentile(5.0) < givenStats.percentile(5.0) ||
            newStats.standardDeviation > givenStats.standardDeviation
    }
}

class PFStrategy4 : PFStrategy {

    override fun isGood(
        givenStats: Descriptives,
        newStats: Descriptives
    ): Boolean {
        return newStats.mean < givenStats.mean
    }
}