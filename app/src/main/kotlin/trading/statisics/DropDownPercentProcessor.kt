package trading.statisics

import trading.indicators.ZigZag
import java.math.BigDecimal

class DropDownPercentProcessor(
    private val zigZag: ZigZag
) : DistributionProcessor<BigDecimal> {

    override fun values(): List<BigDecimal> {
        TODO()
    }
}