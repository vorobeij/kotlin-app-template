package trading.statisics

import org.nield.kotlinstatistics.descriptiveStatistics

class PortfolioFinder2(
    private val strategy: PFStrategy
) : IPortfolioFinder {

    private val processor = DailyInvestmentsPortfolioProfitProcessor(300)
    private fun List<TickerCandles>.descripts() = processor.values(this.map { it.candles }).descriptiveStatistics

    override fun findBestSet(candles: List<TickerCandles>): List<TickerCandles> {
        val resultSet = mutableListOf<TickerCandles>()
        resultSet.add(candles[0])

        for (i in 1 until candles.size) {
            val examinated = listOf(candles[i])
            val expandedSet = mutableListOf<TickerCandles>().apply {
                addAll(resultSet)
                addAll(examinated)
            }

            when {
                strategy.isGivenPerformBetterNew(expandedSet.descripts(), resultSet.descripts()) -> {
                    // do nothing, we have the best result set
                    resultSet.clear()
                    resultSet.addAll(expandedSet)
                }
                strategy.isGivenPerformBetterNew(examinated.descripts(), resultSet.descripts()) -> {
                    resultSet.clear()
                    resultSet.addAll(examinated)
                }
            }
        }

        return resultSet
    }

}

