package trading.statisics

import org.nield.kotlinstatistics.descriptiveStatistics
import org.nield.kotlinstatistics.simpleRegression
import trading.infrastructure.CombinationsProcessor
import trading.infrastructure.SubsetProcessor

class PortfolioFinder4(
    private val strategy: PFStrategy,
    private val k: Int
) : IPortfolioFinder {

    private val processor = DailyInvestmentsPortfolioProfitProcessor(300)
    private fun List<TickerCandles>.descripts() = processor.values(this.map { it.candles }).descriptiveStatistics

    override fun findBestSet(candles: List<TickerCandles>): List<TickerCandles> {

        val res = SubsetProcessor().process(
            candles,
            k * 2,
            k
        ) { first: List<TickerCandles>, other: List<TickerCandles> ->

            var sett = first
            var currentDescripts = sett.descripts()
            var counter = 0

            val sum = mutableListOf<TickerCandles>().apply {
                addAll(first)
                addAll(other)
            }

            CombinationsProcessor().process(sum, k) { set ->
                val newDescripts = set.descripts()
                if (strategy.isGivenPerformBetterNew(newDescripts, currentDescripts)) {
                    currentDescripts = newDescripts
                    sett = set
                    println(
                        "new set is better: ${
                            set.sortedBy { it.ticker }.joinToString(",") { "\"${it.ticker}\"" }
                        }"
                    )
                }
                counter++
            }

            sett.sortedBy { it.ticker }
        }

        return res
    }
}