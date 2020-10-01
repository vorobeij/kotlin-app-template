package trading.strategy

import ru.tinkoff.invest.openapi.models.market.Candle
import java.math.BigDecimal

interface MarketStrategy {

    fun onTick(candles: List<Candle>)

    fun profit(currentPrice: BigDecimal): BigDecimal
}

































