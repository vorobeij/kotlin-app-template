package trading.strategy

import ru.tinkoff.invest.openapi.models.market.Candle
import java.math.BigDecimal

data class MonthlyBuyAndHoldForeverMarketStrategy(
    private val dayBuy: Int
) : MarketStrategy {

    private val orders = mutableListOf<Order>()

    override fun onTick(candles: List<Candle>) {
        val last = candles.last()
        if (last.time.dayOfMonth == dayBuy) {
            orders.add(Order(last.openPrice))
        }
    }

    override fun profit(currentPrice: BigDecimal): BigDecimal {
        val t = currentPrice.toDouble() * orders.size / orders.map { it.price.toDouble() }.sum()
        return BigDecimal(t)
    }
}