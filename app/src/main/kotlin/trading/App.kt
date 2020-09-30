package trading

import org.koin.core.context.startKoin
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.di.marketModule
import trading.report.HtmlReportPrinter
import trading.report.ReportPrinter
import trading.repository.HistoryRequest
import trading.repository.MarketRepository
import java.time.OffsetDateTime

class App(
    private val api: OpenApi
) {

    private val app = startKoin {
        modules(marketModule(api))
    }

    fun start() {

        val marketRepository: MarketRepository = app.koin.get()

        val requests = listOf(
            HistoryRequest(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2009-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            ),
            HistoryRequest(
                ticker = "AMZN",
                from = OffsetDateTime.parse("1999-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )/*,
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
            )*/
        )
        val html = HtmlReportPrinter(
            marketRepository = marketRepository,
            requests = requests
        ).html()
        ReportPrinter(Config.reportDir).print(html)
        error("done")
    }
}
