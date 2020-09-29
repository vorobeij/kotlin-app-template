package trading.cache

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import java.io.File
import java.time.OffsetDateTime

class MarketCacheImpl(
    private val cacheRoot: String,
    private val mapper: ObjectMapper
) : MarketCache {

    class TickerFile(
        private val ticker: String,
        private val from: OffsetDateTime,
        private val to: OffsetDateTime,
        private val interval: CandleInterval
    ) {

        fun encode(): String {
            val delimeter = "--"
            return ticker + delimeter + from + delimeter + to + delimeter + interval
        }
    }

    override fun loadHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): List<Candle>? {
        val tickerFile = TickerFile(ticker, from, to, interval)
        val cacheFile = File(cacheRoot, tickerFile.encode())

        return when {
            cacheFile.exists() -> mapper.readValue(cacheFile, object : TypeReference<List<Candle>>() {})
            else -> null
        }
    }

    override fun saveHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval,
        candles: List<Candle>
    ) {
        mapper.writeValue(File(cacheRoot, TickerFile(ticker, from, to, interval).encode()), candles)
    }
}
