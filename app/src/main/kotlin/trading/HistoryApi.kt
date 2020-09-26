package trading

import ru.tinkoff.invest.openapi.OpenApi
import ru.tinkoff.invest.openapi.models.market.Candle
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import ru.tinkoff.invest.openapi.models.market.HistoricalCandles
import ru.tinkoff.invest.openapi.models.market.Instrument
import java.time.OffsetDateTime
import java.util.Optional

class HistoryApi(
    private val api: OpenApi
) {

    fun loadHistory(
        ticker: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): List<Candle> {

        val instrument = getInstrument(ticker)

        return loadHistoryFull(instrument, from, to, interval).candles
    }

    private fun loadHistoryFull(
        instrument: Instrument,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): HistoricalCandles {
        val intervals = splitTimeIntervalByYears(from, to)
        if (intervals.size == 2) {
            return loadHistory(instrument, from, to, interval)
        } else {
            val hc = loadHistory(instrument, intervals[0], intervals[1], interval)
            var i = 1
            while (i < intervals.size - 1) {
                val newCandles = loadHistory(instrument, intervals[i], intervals[i + 1], interval).candles
                logger.info("got candles ${newCandles.joinToString { it.time.toString() }}")
                hc.candles.addAll(newCandles)
                i++
            }
            return hc
        }
    }

    private fun loadHistory(
        instrument: Instrument,
        from: OffsetDateTime,
        to: OffsetDateTime,
        interval: CandleInterval
    ): HistoricalCandles {
        val candlesOpt = api.marketContext.getMarketCandles(instrument.figi, from, to, interval).join().stream().findFirst()
        val historicalCandles: HistoricalCandles = if (candlesOpt.isEmpty) {
            error("candles empty")
        } else {
            candlesOpt.get()
        }
        return historicalCandles
    }

    fun getInstrument(ticker: String): Instrument {
        logger.info("Searching by ticker $ticker... ")
        val instrumentOpt: Optional<Instrument> = api.marketContext.searchMarketInstrumentsByTicker(ticker)
            .join()
            .instruments
            .stream()
            .findFirst()

        val instrument: Instrument = if (instrumentOpt.isEmpty) {
            logger.severe("This ticker is not found")
            error("")
        } else {
            instrumentOpt.get()
        }
        return instrument
    }
}

