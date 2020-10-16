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
            "AAXN",
            "ABT",
            "ADBE",
            "ADI",
            "ADP",
            "AXP",
            "KHC",
            "DVA",
            "ATVI",
            "ADSK",
            "AMD",
            "AVGO",
            "BABA",
            "BIDU",
            "BIO",
            "BKI",
            "COP",
            "CVX",
            "EBAY",
            "EOG",
            "EPAM",
            "EQIX",
            "F",
            "FB",
            "FDX",
            "GOOG",
            "INTC",
            "KO",
            "BA",
            "JNJ",
            "MAS",
            "LRCX",
            "ZTS",
            "MNST",
            "MA",
            "MCD",
            "MSFT",
            "NFLX",
            "NVDA",
            "PEP",
            "PYPL",
            "QIWI",
            "RDY",
            "SBUX",
            "SEDG",
            "SQ",
            "TSLA",
            "TWTR",
            "V",
            "VLO",
            "WMT",
            "XOM",
            "YNDX",
            "ZEN",
            // "AMZN",
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
