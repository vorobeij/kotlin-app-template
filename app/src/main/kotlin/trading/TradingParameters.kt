package trading

import ru.tinkoff.invest.openapi.models.market.CandleInterval
import java.util.Arrays

class TradingParameters(
    val ssoToken: String,
    val tickers: Array<String?>,
    val candleIntervals: Array<CandleInterval?>,
    val sandboxMode: Boolean
) {

    companion object {

        fun fromProgramArgs(
            ssoTokenArg: String,
            tickersArg: String,
            candleIntervalsArg: String,
            sandboxModeArg: String
        ): TradingParameters {
            val tickers: Array<String?> = tickersArg.split(",").toTypedArray()
            val candleIntervals: Array<CandleInterval?> = Arrays.stream(candleIntervalsArg.split(",").toTypedArray())
                .map { str: String -> parseCandleInterval(str) }
                .toArray { arrayOf<CandleInterval>() }
            require(candleIntervals.size == tickers.size) { "Количество переданных разрешающих интервалов свечей не совпадает с переданным количеством тикеров." }
            val useSandbox = java.lang.Boolean.parseBoolean(sandboxModeArg)
            return TradingParameters(ssoTokenArg, tickers, candleIntervals, useSandbox)
        }

        private fun parseCandleInterval(str: String): CandleInterval {
            return when (str) {
                "1min" -> CandleInterval.ONE_MIN
                "2min" -> CandleInterval.TWO_MIN
                "3min" -> CandleInterval.THREE_MIN
                "5min" -> CandleInterval.FIVE_MIN
                "10min" -> CandleInterval.TEN_MIN
                else -> throw IllegalArgumentException("Не распознан разрешающий интервал!")
            }
        }
    }
}