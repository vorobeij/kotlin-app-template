package trading.repository

import ru.tinkoff.invest.openapi.models.market.CandleInterval
import java.time.OffsetDateTime

data class HistoryRequest(
    val ticker: String,
    val from: OffsetDateTime,
    val to: OffsetDateTime,
    val interval: CandleInterval
)
