package trading.report

import ru.tinkoff.invest.openapi.models.market.Candle

interface HtmlProcessor {

    fun process(
        candles: List<Candle>
    ): HtmlProcessResult
}
