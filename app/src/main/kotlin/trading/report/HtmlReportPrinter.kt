package trading.report

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
import ru.tinkoff.invest.openapi.models.market.CandleInterval
import trading.infrastructure.logger
import trading.repository.HistoryRequest
import trading.repository.MarketRepository
import trading.statisics.DailyInvestmentOutWithStats
import trading.statisics.DailyInvestmentsPortfolioProfitProcessor
import trading.statisics.DailyInvestmentsProfitProcessor
import trading.statisics.IPortfolioFinder
import trading.statisics.PFStrategy1
import trading.statisics.PFStrategy4
import trading.statisics.PortfolioFinder3
import trading.statisics.PortfolioFinder4
import trading.statisics.PortfolioProfitProcessor
import trading.statisics.PortfolioProfitsChartMaxProfitStrategy
import trading.statisics.ProfitDaysProcessor
import trading.statisics.TickerCandles
import trading.strategy.MarketStrategy
import trading.strategy.MarketStrategyHistoryTester
import java.math.BigDecimal
import java.math.MathContext
import java.text.DecimalFormat
import java.time.OffsetDateTime
import java.util.UUID

data class StrategyResult(
    val profit: BigDecimal,
    val name: String
)

private const val chartsFont = "Avenir Next"

class HtmlReportPrinter(
    private val marketRepository: MarketRepository,
    private val tickers: List<String>
) {

    fun html(): String {
        return StringBuilder().appendHTML().html {
            head {
                title("Trading report")
                link(href = "./styles/main.css", rel = "stylesheet")
                script(src = "./js/plotly-latest.min.js") {}
            }
            body {
                /*portfolioSet(
                    listOf(*//*"ADBE", "AVGO", *//*"MA", "NFLX", "NVDA", "V", "VLO"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2018-01-01T10:15:30+01:00"),
                )
                portfolioSet(
                    listOf("BIDU","ADSK","AAXN","AVGO","AAPL"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2018-01-01T10:15:30+01:00"),
                )*/
                /*
        portfolioSet(
            listOf("ADBE", *//*"AMZN",*//* "AVGO", "MA", "NFLX", "NVDA", *//*"TSLA",*//* "V"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2018-01-01T10:15:30+01:00"),
                )
                portfolioSet(
                    listOf("ADBE", *//*"AMZN",*//* "AVGO", "MA", "NFLX", "NVDA", *//*"TSLA",*//* "V"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2020-01-01T10:15:30+01:00"),
                )
                portfolioSet(
                    listOf("AAPL","ADBE","AVGO","MA","NFLX","NVDA","TSLA","V"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2020-01-01T10:15:30+01:00"),
                )
                portfolioSet(
                    listOf("ADBE", "AVGO", "MA", "NFLX", "NVDA", "TSLA", "V"),
                    period = 300,
                    from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2018-01-01T10:15:30+01:00"),
                )

                */

                /*charts(tickers.map {
                    HistoryRequest(
                        ticker = it,
                        from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                        to = OffsetDateTime.parse("2018-01-01T10:15:30+01:00"),
                        interval = CandleInterval.DAY
                    )
                })*/

                // portfolioSet(
                //     listOf("MA","SBUX","ADBE","PYPL"),
                //     period = 300,
                //     from = OffsetDateTime.parse("2010-01-01T10:15:30+01:00"),
                //     to = OffsetDateTime.parse("2015-01-01T10:15:30+01:00"),
                // )

                /*portfolioSet(
                    listOf("ADBE", "AVGO", "MA", "NFLX", "NVDA", *//*"V", "VLO"*//*),
                    period = 300,
                        from = OffsetDateTime.parse("2017-01-01T10:15:30+01:00"),
                        to = OffsetDateTime.parse("2020-01-01T10:15:30+01:00"),
                    )
                portfolioSet(
                    listOf("MA","SBUX","ADBE","PYPL", "BA", "UAL", "EPAM"),
                    period = 300,
                    from = OffsetDateTime.parse("2017-01-01T10:15:30+01:00"),
                    to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                )*/


                charts(tickers.map {
                    HistoryRequest(
                        ticker = it,
                        from = OffsetDateTime.parse("2015-01-01T10:15:30+01:00"),
                        to = OffsetDateTime.parse("2019-01-01T10:15:30+01:00"),
                        interval = CandleInterval.DAY
                    )
                })

                // requests.forEach { charts(it) }
            }
        }.toString()
    }

    private fun FlowContent.charts(historyRequest: HistoryRequest) {
        div(classes = "ticket-section") {
            div(classes = "header") {
                +historyRequest.ticker
            }
            div(classes = "charts-section") {
                // histogramChart(dailyProfit(historyRequest, 360))
                // histogramChart(dailyProfit(historyRequest, 720))
/*                compareStrategies(
                    historyRequest,
                    listOf(
                        MonthlyBuyAndHoldForeverMarketStrategy(15)
                    )
                )*/
                /*histogramChartComparison(
                    "dailyX" + historyRequest.ticker,
                    "daily buy, sell with statistics", listOf(
                        dailyInvestmentOutWithStats(historyRequest, 360, 60, 1.0),
                        dailyInvestmentOutWithStats(historyRequest, 360, 60, 2.0),
                        dailyInvestmentOutWithStats(historyRequest, 360, 60, 2.5),
                        dailyInvestmentOutWithStats(historyRequest, 360, 60, 3.0),
                    )
                )*/
                histogramChartComparison(
                    "daily profits" + historyRequest.ticker,
                    "daily", listOfNotNull(
                        dailyProfit(historyRequest, 300),
                        dailyProfit(historyRequest, 600),
                        dailyProfit(historyRequest, 900),
                        dailyProfit(historyRequest, 1200)
                    )
                )
            }
        }
    }

    private fun FlowContent.charts(historyRequests: List<HistoryRequest>) {
        bestPortfolioSet(historyRequests, PortfolioFinder4(strategy = PFStrategy1(), k = 5), 300)
        bestPortfolioSet(historyRequests, PortfolioFinder4(strategy = PFStrategy4(), k = 5), 300)
        bestPortfolioSet(historyRequests, PortfolioFinder3(strategy = PortfolioProfitsChartMaxProfitStrategy(), k = 3), 300)
        bestPortfolioSet(historyRequests, PortfolioFinder3(strategy = PortfolioProfitsChartMaxProfitStrategy(), k = 4), 300)
        bestPortfolioSet(historyRequests, PortfolioFinder3(strategy = PortfolioProfitsChartMaxProfitStrategy(), k = 5), 300)
    }

    private fun FlowContent.compareStrategies(
        historyRequest: HistoryRequest,
        strategies: List<MarketStrategy>
    ) {
        val candles = marketRepository.loadHistory(historyRequest)
        // todo async
        val results = strategies.map { StrategyResult(MarketStrategyHistoryTester(it, candles).profit(), it.toString()) }
        barsChart(
            BarChartData(
                chartId = historyRequest.ticker + "compareStrategies",
                title = "Strategies",
                data = results
            )
        )
    }

    private fun dailyProfit(
        historyRequest: HistoryRequest,
        period: Long
    ): HistogramChartParams? {
        try {
            val candles = marketRepository.loadHistory(historyRequest)
            val dailyInvestmentsProfitProcessor = DailyInvestmentsProfitProcessor(period)
            val data = dailyInvestmentsProfitProcessor.values(candles)

            return HistogramChartParams(
                chartId = historyRequest.ticker + "dailyProfit" + period,
                title = "$period",
                data = data
            )
        } catch (e: Exception) {
            return null
        }
    }

    private fun dailyProfitPortfolio(
        historyRequests: List<HistoryRequest>,
        period: Long
    ): HistogramChartParams {

        val candles = historyRequests.map { marketRepository.loadHistory(it) }
        val dailyInvestmentsPortfolioProfitProcessor = DailyInvestmentsPortfolioProfitProcessor(period)
        val data = dailyInvestmentsPortfolioProfitProcessor.values(candles.filter { it.size > period * 5 })

        return HistogramChartParams(
            chartId = historyRequests.joinToString("_") { it.ticker } + period,
            title = historyRequests.joinToString("_") { it.ticker } + ", $period days",
            data = data
        )
    }

    data class TickerPrice(
        val ticker: String,
        val price: BigDecimal,
        val lots: Int
    )

    private fun FlowContent.portfolioSet(
        tickers: List<String>,
        period: Long,
        from: OffsetDateTime,
        to: OffsetDateTime
    ) {
        val data = tickers.map { TickerCandles(it, marketRepository.loadHistory(HistoryRequest(it, from, to, CandleInterval.DAY))) }
        val dailyInvestmentsPortfolioProfitProcessor = DailyInvestmentsPortfolioProfitProcessor(period)
        val dataProfit: List<BigDecimal> = dailyInvestmentsPortfolioProfitProcessor.values(data.map { it.candles })

        val title = data.joinToString(",") { "\"${it.ticker}\"" }

        var tickerPrices = tickers.map { TickerPrice(it, marketRepository.getTickerCurrentPrice(it), 0) }
        val prices = tickerPrices.map { it.price }
        val max = prices.descriptiveStatistics.max
        tickerPrices = tickerPrices.map {
            it.copy(lots = (max / it.price.toDouble()).toInt())
        }

        div(classes = "ticket-section") {
            div(classes = "header") {
                +title
            }
            div(classes = "header-summary") {
                +"${from.year} - ${to.year}"
            }
            div(classes = "charts-section") {
                histogramChart(
                    HistogramChartParams(
                        chartId = UUID.randomUUID().toString(),
                        title = "",
                        data = dataProfit
                    )
                )
                lineChart(
                    chartId = UUID.randomUUID().toString(),
                    title = "",
                    data = PortfolioProfitProcessor().values(data)
                )
                div(classes = "header-summary") {
                    table {
                        tickerPrices.forEach {
                            tr {
                                td { +it.ticker }
                                td { +"${it.price}\$" }
                                td { +"${it.lots}" }
                            }
                        }
                        tr {
                            td { +"ideal price:" }
                            td { +"${BigDecimal(max * tickers.size, MathContext(2))}\$" }
                        }
                        tr {
                            td { +"price:" }
                            td {
                                +"${tickerPrices.sumOf { it.price * BigDecimal(it.lots) }}\$"
                            }
                        }
                    }
                }
            }
        }
    }

    private fun FlowContent.bestPortfolioSet(
        historyRequests: List<HistoryRequest>,
        portfolioFinder: IPortfolioFinder,
        period: Long
    ) {

        val candles = historyRequests.map { TickerCandles(it.ticker, marketRepository.loadHistory(it)) }
        val data: List<TickerCandles> = portfolioFinder.findBestSet(candles.filter { it.candles.size > period })
        if (data.isNotEmpty()) {
            val tickers = data.map { it.ticker }.sorted()
            portfolioSet(tickers, 300, OffsetDateTime.parse("2015-01-01T10:15:30+01:00"), OffsetDateTime.parse("2017-01-01T10:15:30+01:00"))
            portfolioSet(tickers, 300, OffsetDateTime.parse("2015-01-01T10:15:30+01:00"), OffsetDateTime.parse("2020-09-01T10:15:30+01:00"))
        } else {
            logger.info("best portfolio set is empty")
        }
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
        val data = dailyInvestmentOutWithStats.values(candles).map { it.periodDays }

        return HistogramChartParams(
            chartId = historyRequest.ticker + "dailyInvestmentOutWithStats" + days + maxDaysAfter + deviationsBandwidth,
            title = "$days + $maxDaysAfter, $deviationsBandwidth",
            data = data
        )
    }

    private fun FlowContent.histogramChart(
        params: HistogramChartParams
    ) {
        div(classes = "chart-container") {
            h3 { +params.title }
            div(classes = "chart") {
                id = params.chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var layout = {
                          font: {
                            family: '$chartsFont',
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

    private fun FlowContent.barsChart(
        params: BarChartData
    ) {
        div(classes = "chart-container") {
            div(classes = "chart") {
                id = params.chartId
            }
            script {
                unsafe {
                    raw(
                        """
                        var data = [
                          {
                            x: [${params.data.joinToString(",") { "'${it.name}'" }}],
                            y: [${params.data.joinToString(",") { it.profit.toString() }}],
                            type: 'bar'
                          }
                        ];
                        
                        Plotly.newPlot('${params.chartId}', data);
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
                            family: '$chartsFont',
                            size: 12,
                            color: '#787b86'
                          }
                        };
    
                        var data = [
                        ${
                            params.mapIndexed { index, it ->
                                """{
                            x: [${it.data.joinToString(",")}],
                            name:"${it.title}",
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

data class LineChartPoint(
    val x: Int,
    val y: BigDecimal,
)

data class BarChartData(
    val chartId: String,
    val title: String,
    val data: List<StrategyResult>
)

data class StatsData(
    val size: Double,
    val mean: Double,
    val percentile_5: Double,
    val percentile_95: Double,
    val standardDeviation: Double,
    val skewness: Double,
    val kurtosis: Double,
)

data class HistogramChartParams(
    val chartId: String,
    val title: String,
    val data: List<Number>,
    val color: String = Palette.blue,
    val opacity: Double = 0.9
)