package trading.cache

import ru.tinkoff.invest.openapi.models.market.Candle
import trading.repository.HistoryRequest

interface MarketCache {

    fun loadHistory(request: HistoryRequest): List<Candle>?

    fun saveHistory(
        request: HistoryRequest,
        candles: List<Candle>
    )
}
