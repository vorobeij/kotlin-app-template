package trading.statisics

import ru.tinkoff.invest.openapi.models.market.Candle

interface ValueProcessor<T : Comparable<T>> {

    fun value(history: List<Candle>): T
}
