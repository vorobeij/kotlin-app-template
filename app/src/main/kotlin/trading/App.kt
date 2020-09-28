package trading

import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.infrastructure.logger
import trading.models.AuthParameters
import java.time.OffsetDateTime

object App {

    @JvmStatic
    fun main(args: Array<String>) {

        val authParameters = AuthParameters(
            ssoToken = args[0],
            sandboxMode = true
        )

        ApiInteractor().runSafely(authParameters) { api ->

            val historyApi = MarketApi(api)
            val candles = historyApi.loadHistory(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
                to = OffsetDateTime.parse("2020-28-09T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
            logger.info(candles.joinToString("\n") { "${it.time} ${it.closePrice}" })
        }
    }
}
