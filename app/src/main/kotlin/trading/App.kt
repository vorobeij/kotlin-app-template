package trading

import org.koin.core.context.startKoin
import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.di.marketModule
import trading.repository.MarketRepository
import trading.statisics.HistogramBuilder
import java.time.OffsetDateTime

class App(
    private val api: OpenApi
) {

    private val app = startKoin {
        modules(marketModule(api))
    }

    fun start() {

        val marketRepository: MarketRepository = app.koin.get()

        val candles = marketRepository.loadHistory(
            ticker = "AAPL",
            from = OffsetDateTime.parse("2018-12-03T10:15:30+01:00"),
            to = OffsetDateTime.parse("2020-09-28T10:15:30+01:00"),
            interval = CandleInterval.DAY
        )

        val b = HistogramBuilder(candles.map { it.closePrice })
        println(b.printConsole())
        println(b.css())
    }
}
