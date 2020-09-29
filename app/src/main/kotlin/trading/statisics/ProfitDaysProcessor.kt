package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle
import java.math.BigDecimal
import java.time.Duration

class ProfitDaysProcessor(
    private val profitPercent: Double
) : DistributionProcessor<Int> {

    override fun values(history: List<Candle>): List<Int> {
        val values = mutableListOf<Int>()

        for (i in history.indices) {
            val targetPrice = history[i].highestPrice * BigDecimal(1 + profitPercent)
            var j = i
            while (j < history.size - 1 && history[j].highestPrice < targetPrice) {
                j++
            }
            val daysDiff = Duration.between(history[i].time, history[j].time).toDays().toInt()
            values.add(daysDiff)
        }

        return values
    }
}
