package trading.statisics

interface IPortfolioFinder {

    fun findBestSet(candles: List<TickerCandles>): List<TickerCandles>
}
