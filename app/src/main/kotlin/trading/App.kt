package trading

import org.koin.core.context.startKoin
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.di.marketModule
import trading.infrastructure.logger
import trading.report.HtmlReportPrinter
import trading.report.ReportPrinter
import trading.repository.HistoryRequest
import trading.repository.MarketRepository
import java.time.OffsetDateTime
import kotlin.system.measureTimeMillis

class App(
    private val api: OpenApi
) {

    private val app = startKoin {
        modules(marketModule(api))
    }

    fun start() {

        val marketRepository: MarketRepository = app.koin.get()

        val tickers = listOf(
            "AAPL",
            "GOOG",
            "AMZN",
            "ADBE",
            "INTC",
            "TSLA",
            "XOM",
            "CVX",
            "COP",
            "VLO",
            "EOG",
            "TCS",
            "NFLX",
            "MSFT",
            "FB",
            "TWTR",
            "V",
            "SQ",
            "MA",
            "AMD",
            "F",
            "FDX",
            "ADSK",
            "AVGO",
            "BABA",
            "BIDU",
            "EBAY",
            "EPAM",
            "NVDA",
            "PYPL",
            "QIWI",
            "YNDX",
        ).distinct()

        val requestss = tickers.map {
            HistoryRequest(
                ticker = it,
                from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        }

        val requests = listOf(
            HistoryRequest(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2009-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "GOOG",
                from = OffsetDateTime.parse("2015-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "AMZN",
                from = OffsetDateTime.parse("1999-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "ADBE",
                from = OffsetDateTime.parse("1999-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "INTC",
                from = OffsetDateTime.parse("2002-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "TSLA",
                from = OffsetDateTime.parse("2011-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "XOM",
                from = OffsetDateTime.parse("1980-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "CVX",
                from = OffsetDateTime.parse("1980-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "COP",
                from = OffsetDateTime.parse("1980-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "EOG",
                from = OffsetDateTime.parse("1980-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "VLO",
                from = OffsetDateTime.parse("1987-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        )

        val t = measureTimeMillis {
            val html = HtmlReportPrinter(
                marketRepository = marketRepository,
                requests = requestss
            ).html()
            ReportPrinter(Config.reportDir).print(html)
        }
        logger.info("execution time = $t ms (${t / 1000} sec)")
        error("done")
    }
}
