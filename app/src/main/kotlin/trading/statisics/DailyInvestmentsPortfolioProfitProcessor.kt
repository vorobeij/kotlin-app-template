package trading.statisics

import org.nield.kotlinstatistics.average
import ru.tinkoff.invest.openapi.models.market.Candle
import trading.infrastructure.firstAfter
import java.math.BigDecimal

class DailyInvestmentsPortfolioProfitProcessor(
    private val investmentPeriodDays: Long
) {

    fun values(
        hists: List<List<Candle>>
    ): List<BigDecimal> {
        val values = mutableListOf<BigDecimal>()

        val minSize = hists.minByOrNull { it.size }!!.size
        val histories = hists.map { it.subList(it.size - minSize, it.size) }
        val hist = histories.minByOrNull { it.size }!!
        val start = 0
        val end = hist.lastIndex

        val latestTime = hist[end].time.minusDays(investmentPeriodDays + 1)
        val lastIndex = hist.firstAfter(start, end) { !it.time.isBefore(latestTime) }

        for (i in start until lastIndex) {
            val afterPeriodTime = hist[i].time.plusDays(investmentPeriodDays)

            val profits = histories.map { history ->
                val buyPrice = history[i].highestPrice
                val afterItem = history[history.firstAfter(i, end) { !it.time.isBefore(afterPeriodTime) }]
                val afterPeriodPrice = afterItem.highestPrice
                ProfitData(buyPrice, afterPeriodPrice)
            }

            val profit = profits.map { it.sell / it.buy }.average()//profits.map { it.sell }.sum() / profits.map { it.buy }.sum()
            values.add(profit)
        }

        return values
    }
}

private data class ProfitData(
    val buy: BigDecimal,
    val sell: BigDecimal
)

data class TickerCandles(
    val ticker: String,
    val candles: List<Candle>
)
