package trading.repository

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import ru.tinkoff.invest.openapi.models.market.Instrument
import trading.api.MarketApi
import trading.cache.MarketCache
import java.time.OffsetDateTime

class MarketRepository(
    private val marketApi: MarketApi,
    private val marketCache: MarketCache
) : MarketApi {

    override fun loadHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): List<Candle> {
        return marketCache.loadHistory(ticker, from, to, interval)
            ?: marketApi.loadHistory(ticker, from, to, interval).also {
                marketCache.saveHistory(ticker, from, to, interval, it)
            }
    }

    override fun getInstrument(ticker: String): Instrument {
        return marketApi.getInstrument(ticker)
    }
}
