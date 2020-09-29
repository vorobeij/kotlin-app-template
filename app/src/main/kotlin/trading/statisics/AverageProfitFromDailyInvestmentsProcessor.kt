package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle

class AverageProfitFromDailyInvestmentsProcessor(
    private val dailyInvestmentsProfitProcessor: DailyInvestmentsProfitProcessor
) : ValueProcessor<Double> {

    override fun value(history: List<Candle>): Double {
        val hist = dailyInvestmentsProfitProcessor.values(history).map { it.toDouble() }
        return hist.average()
    }
}
