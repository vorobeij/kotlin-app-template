package trading.api

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import ru.tinkoff.invest.openapi.models.market.Instrument
import java.time.OffsetDateTime

interface MarketApi {

    fun loadHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): List<Candle>

    fun getInstrument(ticker: String): Instrument
}
