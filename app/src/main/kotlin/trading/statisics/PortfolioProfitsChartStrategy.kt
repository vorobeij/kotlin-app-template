package trading.statisics

import trading.report.LineChartPoint

interface PortfolioProfitsChartStrategy {

    fun isBetter(
        old: List<LineChartPoint>,
        new: List<LineChartPoint>
    ): Boolean
}