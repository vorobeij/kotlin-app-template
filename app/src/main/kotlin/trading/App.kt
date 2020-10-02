package trading

import org.koin.core.context.startKoin
import ru.tinkoff.invest.openapi.OpenApi
import trading.di.marketModule
import trading.infrastructure.logger
import trading.report.HtmlReportPrinter
import trading.report.ReportPrinter
import trading.repository.MarketRepository
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
            "ADBE",
            "ADSK",
            "AMD",
            // "AMZN",
            "AVGO",
            "WMT",
            "BABA",
            "BIDU",
            "COP",
            "CVX",
            "EBAY",
            "EOG",
            "EPAM",
            "F",
            "FB",
            "FDX",
            "GOOG",
            "INTC",
            "MA",
            "MSFT",
            "NFLX",
            "NVDA",
            "PYPL",
            "QIWI",
            "SQ",
            "TCS",
            "TSLA",
            "TWTR",
            "V",
            "VLO",
            "XOM",
            "YNDX",
        ).distinct()

        val t = measureTimeMillis {
            val html = HtmlReportPrinter(
                marketRepository = marketRepository,
                tickers = tickers
            ).html()
            ReportPrinter(Config.reportDir).print(html)
        }
        logger.info("execution time = $t ms (${t / 1000} sec)")
        error("done")
    }
}
