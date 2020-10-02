package trading.api

import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.Instrument
import trading.repository.HistoryRequest
import java.math.BigDecimal

interface MarketApi {

    fun loadHistory(request: HistoryRequest): List<Candle>

    fun getInstrument(ticker: String): Instrument

    fun getTickerCurrentPrice(ticker: String): BigDecimal
}
