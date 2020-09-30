package trading.report

import ru.tinkoff.invest.openapi.models.market.Candle
import trading.infrastructure.logger
import trading.statisics.AverageProfitFromDailyInvestmentsProcessor
import trading.statisics.DailyInvestmentsProfitProcessor
import trading.statisics.HistogramBuilder
import trading.statisics.printer.ConsoleHistogramPrinter
import trading.statisics.printer.HistogramPrinter

class DailyProfitAfterYearHtmlProcessor : HtmlProcessor {

    private val printer: HistogramPrinter = ConsoleHistogramPrinter()

    override fun process(candles: List<Candle>): HtmlProcessResult {
        val html = profitPerPeriod(candles, 1 * 360)
        return HtmlProcessResult(html, "")
    }

    private fun profitPerPeriod(
        candles: List<Candle>,
        periodDays: Long
    ): String {
        val profitDaysProcessor = DailyInvestmentsProfitProcessor(periodDays)

        logger.info(profitDaysProcessor.values(candles).joinToString(","))

        val hist = HistogramBuilder(profitDaysProcessor.values(candles)).buildHistogram()
        var res = ""
        res += "$periodDays, ${hist.sumBy { it.count }}\n${printer.print(hist)}"
        res += "Average profit = %.2f".format(AverageProfitFromDailyInvestmentsProcessor(profitDaysProcessor).value(candles))
        return res
    }
}
