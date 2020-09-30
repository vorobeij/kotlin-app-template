package trading.report

import kotlinx.html.FlowContent
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.id
import kotlinx.html.link
import kotlinx.html.script
import kotlinx.html.stream.appendHTML
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.title
import kotlinx.html.tr
import kotlinx.html.unsafe
import org.nield.kotlinstatistics.descriptiveStatistics
import trading.repository.HistoryRequest
import trading.repository.MarketRepository
import trading.statisics.DailyInvestmentOutWithStats
import trading.statisics.DailyInvestmentsProfitProcessor
import trading.statisics.ProfitDaysProcessor
import java.text.DecimalFormat

class HtmlReportPrinter(
    private val marketRepository: MarketRepository,
    private val requests: List<HistoryRequest>
) {

    fun html(): String {
        return StringBuilder().appendHTML().html {
            head {
                title("Trading report")
                link(href = "./styles/main.css", rel = "stylesheet")
                script(src = "./js/plotly-latest.min.js") {}
            }
            body {
                requests.forEach { charts(it) }
            }
        }.toString()
    }

    private fun FlowContent.charts(historyRequest: HistoryRequest) {
        div(classes = "ticket-section") {
            div(classes = "header") {
                +historyRequest.ticker
            }
            div(classes = "charts-section") {
                dailyProfit(historyRequest, 360)
                histogramChartComparison(
                    "dailyX" + historyRequest.ticker,
                    "daily buy, sell with statistics", listOf(
                        dailyInvestmentOutWithStats(historyRequest, 360, 60, 3.0),
                        dailyInvestmentOutWithStats(historyRequest, 360, 180, 3.0)
                    )
                )
            }
        }
    }

    private fun FlowContent.dailyProfit(
        historyRequest: HistoryRequest,
        period: Long
    ) {
        val candles = marketRepository.loadHistory(historyRequest)
        val dailyInvestmentsProfitProcessor = DailyInvestmentsProfitProcessor(period)
        val data = dailyInvestmentsProfitProcessor.values(candles)

        histogramChart(
            HistogramChartParams(
                chartId = historyRequest.ticker + "dailyProfit" + period,
                title = "Daily Buy - $period days profits",
                data = data
            )
        )
    }

    private fun FlowContent.profitReturnTime(
        historyRequest: HistoryRequest,
        profit: Double
    ) {
        val candles = marketRepository.loadHistory(historyRequest)
        val profitDaysProcessor = ProfitDaysProcessor(profit)
        val data = profitDaysProcessor.values(candles)
        histogramChart(
            HistogramChartParams(
                chartId = historyRequest.ticker + "dailyProfit" + profit,
                title = "Profit return: $profit",
                data = data
            )
        )
    }

    private fun dailyInvestmentOutWithStats(
        historyRequest: HistoryRequest,
        days: Long,
        maxDaysAfter: Long,
        deviationsBandwidth: Double
    ): HistogramChartParams {
        val candles = marketRepository.loadHistory(historyRequest)
        val dailyInvestmentOutWithStats = DailyInvestmentOutWithStats(days, maxDaysAfter, deviationsBandwidth)
        val data = dailyInvestmentOutWithStats.values(candles)

        return HistogramChartParams(
            chartId = historyRequest.ticker + "dailyInvestmentOutWithStats" + days + maxDaysAfter + deviationsBandwidth,
            title = "$days + $maxDaysAfter, $deviationsBandwidth",
            data = data
        )
    }

    data class HistogramChartParams(
        val chartId: String,
        val title: String,
        val data: List<Number>,
        val color: String = Palette.blue,
        val opacity: Double = 0.9
    )

    private fun FlowContent.histogramChart(
        params: HistogramChartParams
    ) {
        div(classes = "chart-container") {
            div(classes = "chart") {
                id = params.chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var layout = {
                          title: '${params.title}',
                          font: {
                            family: 'Trebuchet MS,roboto,ubuntu,sans-serif',
                            size: 12,
                            color: '#787b86'
                          }
                        };
    
                        var data = [{
                            x: [${params.data.joinToString(",")}],
                            type: 'histogram',
                            opacity: ${params.opacity},
                            marker: {
                             color: '${params.color}',
                            }
                          }];
                        Plotly.newPlot('${params.chartId}', data, layout);
                    """.trimIndent()
                    )
                }
            }

            val descriptiveStatistics = params.data.descriptiveStatistics

            val stats = listOf<Pair<String, Double>>(
                "size" to descriptiveStatistics.size.toDouble(),
                "mean" to descriptiveStatistics.mean,
                "percentile(5)" to descriptiveStatistics.percentile(5.0),
                "percentile(95)" to descriptiveStatistics.percentile(95.0),
                "standardDeviation" to descriptiveStatistics.standardDeviation,
                "skewness" to descriptiveStatistics.skewness,
                "kurtosis" to descriptiveStatistics.kurtosis,
            )
            div(classes = "chart-summary") {
                table {
                    stats.forEach {
                        tr {
                            td { +it.first }
                            td { +DecimalFormat("#.##").format(it.second) }
                        }
                    }
                }
            }
        }
    }

    private fun FlowContent.histogramChartComparison(
        chartId: String,
        title: String,
        params: List<HistogramChartParams>
    ) {
        div(classes = "chart-container") {
            div(classes = "chart") {
                id = chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var layout = {
                          title: '${title}',
                          barmode: "overlay",
                          font: {
                            family: 'Trebuchet MS,roboto,ubuntu,sans-serif',
                            size: 12,
                            color: '#787b86'
                          }
                        };
    
                        var data = [
                        ${
                            params.mapIndexed { index, it ->
                                """{
                            x: [${it.data.joinToString(",")}],
                            type: 'histogram',
                            opacity: ${1.0 / params.size},
                            marker: {
                             color: '${Palette.color(index)}',
                            }
                          }"""
                            }.joinToString(",")
                        }
                        ];
                        Plotly.newPlot('${chartId}', data, layout);
                    """.trimIndent()
                    )
                }
            }

            val stats = params.map {
                val descriptiveStatistics = it.data.descriptiveStatistics

                StatsData(
                    descriptiveStatistics.size.toDouble(),
                    descriptiveStatistics.mean,
                    descriptiveStatistics.percentile(5.0),
                    descriptiveStatistics.percentile(95.0),
                    descriptiveStatistics.standardDeviation,
                    descriptiveStatistics.skewness,
                    descriptiveStatistics.kurtosis,
                )
            }

            val decimalFormat = DecimalFormat("#.##")

            div(classes = "chart-summary") {
                table {
                    fun row(
                        title: String,
                        fields: List<Double>
                    ) {
                        tr {
                            td { +title }
                            fields.forEach { td { +decimalFormat.format(it) } }
                        }
                    }

                    tr {
                        td {}
                        params.forEach {
                            td { +it.title }
                        }
                    }

                    row("size", stats.map { it.size })
                    row("mean", stats.map { it.mean })
                    row("percentile_5", stats.map { it.percentile_5 })
                    row("percentile_95", stats.map { it.percentile_95 })
                    row("standardDeviation", stats.map { it.standardDeviation })
                    row("skewness", stats.map { it.skewness })
                    row("kurtosis", stats.map { it.kurtosis })
                }
            }
        }
    }
}

data class StatsData(
    val size: Double,
    val mean: Double,
    val percentile_5: Double,
    val percentile_95: Double,
    val standardDeviation: Double,
    val skewness: Double,
    val kurtosis: Double,
)
