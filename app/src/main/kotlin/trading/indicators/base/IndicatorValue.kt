package trading.indicators.base

import java.math.BigDecimal
import java.time.OffsetDateTime

data class IndicatorValue(
    val time: OffsetDateTime,
    val price: BigDecimal
)