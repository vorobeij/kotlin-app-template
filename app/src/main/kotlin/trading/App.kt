package trading

import ru.tinkoff.invest.openapi.models.market.CandleInterval
import java.time.OffsetDateTime

object App {

    @JvmStatic
    fun main(args: Array<String>) {

        val parameters = TradingParameters(
            ssoToken = args[0],
            tickers = arrayOf("AAPL"),
            candleIntervals = arrayOf(CandleInterval.DAY),
            sandboxMode = true
        )

        ApiInteractor().runSafely(parameters) { api ->

            val historyApi = HistoryApi(api)
            val candles = historyApi.loadHistory(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
                to = OffsetDateTime.now(),
                interval = CandleInterval.DAY
            )
            logger.info(candles.joinToString("\n") { "${it.time} ${it.closePrice}" })
        }
    }
}

