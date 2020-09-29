package trading

import org.koin.core.context.startKoin
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.di.marketModule
import trading.infrastructure.logger
import trading.repository.MarketRepository
import trading.statisics.AverageProfitFromDailyInvestmentsProcessor
import trading.statisics.DailyInvestmentsProfitProcessor
import trading.statisics.HistogramBuilder
import trading.statisics.ProfitDaysProcessor
import trading.statisics.printer.ConsoleHistogramPrinter
import trading.statisics.printer.HistogramPrinter
import java.math.BigDecimal
import java.time.OffsetDateTime

class App(
    private val api: OpenApi
) {

    private val app = startKoin {
        modules(marketModule(api))
    }

    private val printer: HistogramPrinter = ConsoleHistogramPrinter()

    fun start() {

        val marketRepository: MarketRepository = app.koin.get()

        report(
            "AAPL",
            marketRepository.loadHistory(
                ticker = "AAPL",
                from = OffsetDateTime.parse("2009-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        )

        report(
            "AMZN",
            marketRepository.loadHistory(
                ticker = "AMZN",
                from = OffsetDateTime.parse("1999-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        )

        report(
            "ADBE",
            marketRepository.loadHistory(
                ticker = "ADBE",
                from = OffsetDateTime.parse("1999-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        )

        report(
            "INTC",
            marketRepository.loadHistory(
                ticker = "INTC",
                from = OffsetDateTime.parse("1980-01-01T10:15:30+01:00"),
                to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                interval = CandleInterval.DAY
            )
        )
    }

    private fun report(
        ticker: String,
        candles: List<Candle>
    ) {
        /*    profit(ticker, candles, 0.05)
            profit(ticker, candles, 0.1)
            profit(ticker, candles, 0.2)
            profit(ticker, candles, 0.3)
            profit(ticker, candles, 0.8)*/

        // profitPerPeriod(ticker, candles, 180)
        profitPerPeriod(ticker, candles, 1 * 360)
        // profitPerPeriod(ticker, candles, 3 * 360)
        // profitPerPeriod(ticker, candles, 5 * 360)
        // profitPerPeriod(ticker, candles, 7 * 360)
    }

    private fun profit(
        ticker: String,
        candles: List<Candle>,
        profit: Double
    ) {
        val profitDaysProcessor = ProfitDaysProcessor(profit)
        val hist = HistogramBuilder(profitDaysProcessor.values(candles).map { BigDecimal(it) }).buildHistogram()
        logger.info(ticker + " - " + profit + "\n" + printer.print(hist))
    }

    private fun profitPerPeriod(
        ticker: String,
        candles: List<Candle>,
        periodDays: Long
    ) {
        val profitDaysProcessor = DailyInvestmentsProfitProcessor(periodDays)
        val hist = HistogramBuilder(profitDaysProcessor.values(candles)).buildHistogram()
        logger.info("$ticker - $periodDays, ${hist.sumBy { it.count }}\n${printer.print(hist)}")
        logger.info("$ticker Average profit = %.2f".format(AverageProfitFromDailyInvestmentsProcessor(profitDaysProcessor).value(candles)))
    }
}
