package trading.statisics

class PortfolioProfitsChartFinder(
    private val strategy: PortfolioProfitsChartStrategy
) : IPortfolioFinder {

    private val processor = PortfolioProfitProcessor()

    override fun findBestSet(candles: List<TickerCandles>): List<TickerCandles> {
        val goodStocks = mutableListOf<TickerCandles>()
        // 1 get stats for a given set:
        val givenSetStats = processor.values(candles)
        val removedStocks = mutableListOf<TickerCandles>()

        while (removedStocks.size != candles.size) {
            val newCandles = candles.toMutableList()
            val toRemove = newCandles.first { it !in removedStocks }
            newCandles.remove(toRemove)
            removedStocks.add(toRemove)

            // get metrics and compare with given set numbers
            val newStats = processor.values(newCandles)
            if (!strategy.isBetter(givenSetStats, newStats)) {
                goodStocks.add(toRemove)
            }

            newCandles.add(toRemove)
        }
        return goodStocks.sortedBy { it.ticker }
    }
}