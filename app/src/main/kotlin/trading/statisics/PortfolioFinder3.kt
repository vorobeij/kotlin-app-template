package trading.statisics

import org.nield.kotlinstatistics.descriptiveStatistics
import org.nield.kotlinstatistics.randomDistinct

class PortfolioFinder3(
    investmentPeriodDays: Long
) : IPortfolioFinder {

    // data class X(
    //     val candles: List<TickerCandles>,
    //     val
    // )
    private val processor = DailyInvestmentsPortfolioProfitProcessor(investmentPeriodDays)

    override fun findBestSet(candles: List<TickerCandles>): List<TickerCandles> {

        var c = candles
        while (c.size > 5) {
            // get n random sets
            c = (c.indices).map { candles.randomDistinct(c.size - 1) }.maxByOrNull { it.descripts().mean }!!
        }

        return c.sortedBy { it.ticker }
    }

    private fun List<TickerCandles>.descripts() = processor.values(this.map { it.candles }).descriptiveStatistics
}