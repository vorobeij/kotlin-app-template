package trading.api

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.Instrument
import trading.repository.HistoryRequest

interface MarketApi {

    fun loadHistory(request: HistoryRequest): List<Candle>

    fun getInstrument(ticker: String): Instrument
}
