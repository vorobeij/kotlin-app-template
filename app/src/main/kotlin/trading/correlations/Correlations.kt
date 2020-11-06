package trading.correlations

import kotlinx.html.FlowContent
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h3
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
import trading.Config
import trading.infrastructure.ResourcesUtils
import trading.infrastructure.logger
import trading.report.LineChartPoint
import trading.report.Palette
import trading.report.ReportPrinter
import java.text.DecimalFormat
import java.text.SimpleDateFormat

object Correlations {

    @JvmStatic
    fun main(args: Array<String>) {

        val html = HtmlChartsPrinter().html()
        ReportPrinter(Config.reportDir + "/charts").print(html)
    }
}

class HtmlChartsPrinter() {

    fun html(): String {
        return StringBuilder().appendHTML().html {
            head {
                title("Charts")
                link(href = "./styles/main.css", rel = "stylesheet")
                script(src = "./js/plotly-latest.min.js") {}
            }
            body {
                usdVsOil()
            }
        }.toString()
    }

    private fun FlowContent.usdVsOil() {
        logger.info("start")
        val parser = SimpleDateFormat("yyyy MMM")
        val startDate = parser.parse("2018 Jan")
        val endDate = parser.parse("2021 Jan")
        val usdRub = TickerLoader().load(ResourcesUtils.getFile("usdrub.csv"), "yyyy-MM-dd", startDate, endDate).toMutableList()
        val oil = TickerLoader().load(ResourcesUtils.getFile("oil.csv"), "MMM yyyy", startDate, endDate).toMutableList()
        TickerLoader().alignStart(usdRub, oil)

        linesChart(
            "usdrub",
            "usdrub vs oil, corr = ${GFG.correlationCoefficient(usdRub.map { it.price }.toDoubleArray(), oil.map { it.price }.toDoubleArray(), usdRub.size)}",
            listOf(
                LineChartData(Palette.blue, "USD", usdRub.map { it.price }.toDoubleArray().normalized().mapIndexed { index, d -> LineChartPoint(index, d.toBigDecimal()) }),
                LineChartData(Palette.green, "Oil", oil.map { it.price }.toDoubleArray().normalized().mapIndexed { index, d -> LineChartPoint(index, d.toBigDecimal()) })
            )
        )
        linesChart(
            "usdrub not normalized",
            "usdrub vs oil",
            listOf(
                LineChartData(Palette.blue, "USD", usdRub.map { it.price }.toDoubleArray().mapIndexed { index, d -> LineChartPoint(index, d.toBigDecimal()) }),
                LineChartData(Palette.green, "Oil", oil.map { it.price }.toDoubleArray().mapIndexed { index, d -> LineChartPoint(index, d.toBigDecimal()) })
            )
        )
    }

    data class LineChartData(
        val color: String,
        val name: String,
        val data: List<LineChartPoint>
    )

    private fun FlowContent.linesChart(
        chartId: String,
        title: String,
        data: List<LineChartData>
    ) {
        div(classes = "chart-container") {
            h3 { +title }
            div(classes = "chart") {
                id = chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var data = [${
                            data.joinToString(",") { list ->
                                """{
                          x: [${list.data.joinToString(",") { it.x.toString() }}],
                          y: [${list.data.joinToString(",") { it.y.toString() }}],
                          type: 'scatter',
                          line: {
                            width: 1
                          },
                          color:'${list.color}',
                          name:"${list.name}"
                        }"""
                            }
                        }];
                        
                        Plotly.newPlot('$chartId', data);
                    """.trimIndent()
                    )
                }
            }
        }
    }

    private fun FlowContent.lineChart(
        chartId: String,
        title: String,
        data: List<LineChartPoint>
    ) {
        div(classes = "chart-container") {
            h3 { +title }
            div(classes = "chart") {
                id = chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var trace1 = {
                          x: [${data.joinToString(",") { it.x.toString() }}],
                          y: [${data.joinToString(",") { it.y.toString() }}],
                          type: 'scatter',
                          line: {
                            width: 1
                          }
                        };
                        
                        var data = [trace1];
                        
                        Plotly.newPlot('$chartId', data);
                    """.trimIndent()
                    )
                }
            }
            val d = data.map { it.y }.descriptiveStatistics
            val stats = listOf(
                "max" to d.max
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
}

fun DoubleArray.normalized(): DoubleArray {
    val min = get(0)
    for (i in indices) {
        this[i] = this[i] / min - 1
    }
    return this
}

internal object GFG {

    // function that returns correlation coefficient.
    fun correlationCoefficient(
        X: DoubleArray,
        Y: DoubleArray,
        n: Int
    ): Float {
        var sum_X = 0.0
        var sum_Y = 0.0
        var sum_XY = 0.0
        var squareSum_X = 0.0
        var squareSum_Y = 0.0
        for (i in 0 until n) {
            // sum of elements of array X.
            sum_X += X[i]

            // sum of elements of array Y.
            sum_Y += Y[i]

            // sum of X[i] * Y[i].
            sum_XY += X[i] * Y[i]

            // sum of square of array elements.
            squareSum_X += X[i] * X[i]
            squareSum_Y += Y[i] * Y[i]
        }

        // use formula for calculating correlation
        // coefficient.
        return (n * sum_XY - sum_X * sum_Y).toFloat() /
            Math.sqrt(
                (n * squareSum_X -
                    sum_X * sum_X) * (n * squareSum_Y -
                    sum_Y * sum_Y).toDouble()
            ).toFloat()
    }
}

