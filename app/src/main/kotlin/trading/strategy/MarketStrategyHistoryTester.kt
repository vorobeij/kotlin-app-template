package trading.strategy

import ru.tinkoff.invest.openapi.models.market.Candle
import java.math.BigDecimal

class MarketStrategyHistoryTester(
    private val strategy: MarketStrategy,
    private val candles: List<Candle>
) {

    fun profit(): BigDecimal {

        val historyCandles = mutableListOf<Candle>()
        candles.forEach {
            historyCandles.add(it)
            strategy.onTick(historyCandles)
        }
        return strategy.profit(historyCandles.last().openPrice)
    }
}