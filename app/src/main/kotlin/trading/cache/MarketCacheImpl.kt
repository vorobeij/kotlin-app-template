package trading.cache

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ru.tinkoff.invest.openapi.models.market.Candle
import trading.repository.HistoryRequest
import java.io.File

class MarketCacheImpl(
    private val cacheRoot: String,
    private val mapper: ObjectMapper
) : MarketCache {

    override fun loadHistory(
        request: HistoryRequest
    ): List<Candle>? {
        val cacheFile = File(cacheRoot, request.encode())

        return when {
            cacheFile.exists() -> mapper.readValue(cacheFile, object : TypeReference<List<Candle>>() {})
            else -> null
        }
    }

    override fun saveHistory(
        request: HistoryRequest,
        candles: List<Candle>
    ) {
        mapper.writeValue(File(cacheRoot, request.encode()), candles)
    }

    private fun HistoryRequest.encode(): String {
        val delimiter = "--"
        return ticker + delimiter + from + delimiter + to + delimiter + interval
    }
}
