package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle

interface DistributionProcessor<T : Comparable<T>> {

    fun values(history: List<Candle>): List<T>
}
