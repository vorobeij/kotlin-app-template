package trading.indicators

import ru.tinkoff.invest.openapi.models.market.Candle
import trading.indicators.base.Indicator
import trading.indicators.base.IndicatorValue

class ZigZag(
    val deviation: Float,
    val depth: Int
) : Indicator {

    override fun values(
        history: List<Candle>
    ): List<IndicatorValue> {
        TODO()
    }
}