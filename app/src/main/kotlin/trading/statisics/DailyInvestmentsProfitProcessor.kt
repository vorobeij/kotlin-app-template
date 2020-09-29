package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle
import java.math.BigDecimal
import java.math.MathContext

class DailyInvestmentsProfitProcessor(
    private val investmentPeriodDays: Long
) : DistributionProcessor<BigDecimal> {

    private val mc = MathContext(2)

    override fun values(history: List<Candle>): List<BigDecimal> {
        val values = mutableListOf<BigDecimal>()

        val latestTime = history.last().time.minusDays(investmentPeriodDays + 1)
        val lastIndex = history.indexOfFirst { it.time.isAfter(latestTime) }

        for (i in 0 until lastIndex) {
            val buyPrice = history[i].highestPrice
            val afterPeriodTime = history[i].time.plusDays(investmentPeriodDays)
            val afterPeriodPrice = history[history.indexOfFirst { it.time.isAfter(afterPeriodTime) }].highestPrice
            val profit = BigDecimal(afterPeriodPrice.toDouble() / buyPrice.toDouble(), mc)
            values.add(profit)
        }

        return values
    }
}
