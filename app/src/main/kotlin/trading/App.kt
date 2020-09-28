package trading

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.api.MarketApiImpl
import trading.cache.MarketCacheImpl
import trading.infrastructure.ApiInteractor
import trading.infrastructure.logger
import trading.models.AuthParameters
import trading.repository.MarketRepository
import java.time.OffsetDateTime

object App {

    @JvmStatic
    fun main(args: Array<String>) {

        val authParameters = AuthParameters(
            ssoToken = args[0],
            sandboxMode = true
        )

        ApiInteractor().runSafely(authParameters) { api ->

            // todo use Koin?
            val marketApiImpl = MarketApiImpl(api)
            val mapper = ObjectMapper().apply {
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                registerModule(JavaTimeModule())
                enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
            }
            val marketCacheImpl = MarketCacheImpl("caches", mapper)
            val marketRepository = MarketRepository(
                marketApiImpl,
                marketCacheImpl
            )

            val candles = marketRepository.loadHistory(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
                to = OffsetDateTime.parse("2020-09-28T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
            logger.info(candles.joinToString("\n") { "${it.time} ${it.closePrice}" })
        }
    }
}
