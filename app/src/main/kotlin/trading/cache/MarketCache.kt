package trading.cache

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import java.time.OffsetDateTime

interface MarketCache {

    fun loadHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): List<Candle>?

    fun saveHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval,
        candles: List<Candle>
    )
}
