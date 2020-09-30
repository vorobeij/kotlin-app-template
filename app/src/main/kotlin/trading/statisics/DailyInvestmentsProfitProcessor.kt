package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle
import trading.infrastructure.firstAfter
import java.math.BigDecimal
import java.math.MathContext

class DailyInvestmentsProfitProcessor(
    private val investmentPeriodDays: Long
) : DistributionProcessor<BigDecimal> {

    private val mc = MathContext(2)

    override fun values(
        history: List<Candle>,
        start: Int,
        end: Int
    ): List<BigDecimal> {
        val values = mutableListOf<BigDecimal>()

        val latestTime = history[end].time.minusDays(investmentPeriodDays + 1)
        val lastIndex = history.firstAfter(start, end) { !it.time.isBefore(latestTime) }

        for (i in start until lastIndex) {
            val buyPrice = history[i].highestPrice
            val afterPeriodTime = history[i].time.plusDays(investmentPeriodDays)

            val afterPeriodPrice = history[history.firstAfter(i, end) { !it.time.isBefore(afterPeriodTime) }].highestPrice
            val profit = BigDecimal(afterPeriodPrice.toDouble() / buyPrice.toDouble(), mc)
            values.add(profit)
        }

        return values
    }
}
