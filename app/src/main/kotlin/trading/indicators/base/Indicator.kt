package trading.indicators.base

import ru.tinkoff.invest.openapi.models.market.Candle

interface Indicator {

    fun values(
        history: List<Candle>
    ): List<IndicatorValue>
}
