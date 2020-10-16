package trading.statisics

import org.nield.kotlinstatistics.descriptiveStatistics
import trading.report.LineChartPoint

class PortfolioProfitsChartMaxProfitStrategy : PortfolioProfitsChartStrategy {

    override fun isBetter(
        old: List<LineChartPoint>,
        new: List<LineChartPoint>
    ): Boolean {
        return old.maxOf { it.y } < new.maxOf { it.y }
    }
}