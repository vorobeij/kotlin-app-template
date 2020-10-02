package trading.repository

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.Instrument
import trading.api.MarketApi
import trading.cache.MarketCache
import java.math.BigDecimal

class MarketRepository(
    private val marketApi: MarketApi,
    private val marketCache: MarketCache
) : MarketApi {

    override fun loadHistory(
        request: HistoryRequest
    ): List<Candle> {
        return marketCache.loadHistory(request) ?: marketApi.loadHistory(request).also {
            marketCache.saveHistory(request, it)
        }
    }

    override fun getInstrument(ticker: String): Instrument {
        return marketApi.getInstrument(ticker)
    }

    override fun getTickerCurrentPrice(ticker: String): BigDecimal {
        return marketApi.getTickerCurrentPrice(ticker)
    }
}
