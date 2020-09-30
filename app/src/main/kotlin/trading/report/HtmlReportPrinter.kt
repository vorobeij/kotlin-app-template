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
import kotlinx.html.title
import kotlinx.html.unsafe
import trading.repository.HistoryRequest
import trading.repository.MarketRepository
import trading.statisics.DailyInvestmentsProfitProcessor
import trading.statisics.ProfitDaysProcessor

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
                dailyProfit(historyRequest, 2 * 360)
                dailyProfit(historyRequest, 5 * 360)
                profitReturnTime(historyRequest, 0.2)
                profitReturnTime(historyRequest, 0.5)
                profitReturnTime(historyRequest, 0.8)
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

        chart(
            historyRequest.ticker + "dailyProfit" + period,
            "Daily Buy - $period days profits",
            data
        )
    }

    private fun FlowContent.profitReturnTime(
        historyRequest: HistoryRequest,
        profit: Double
    ) {
        val candles = marketRepository.loadHistory(historyRequest)
        val profitDaysProcessor = ProfitDaysProcessor(profit)
        val data = profitDaysProcessor.values(candles)
        chart(
            historyRequest.ticker + "dailyProfit" + profit,
            "Profit return: $profit",
            data
        )
    }

    private fun FlowContent.chart(
        chartId: String,
        title: String,
        data: List<Number>
    ) {
        div(classes = "chart") {
            id = chartId
        }
        script {
            unsafe {
                raw(
                    """
                        var x = [${data.joinToString(",")}];
                        var layout = {
                          title: '$title',
                          font: {
                            family: 'Trebuchet MS,roboto,ubuntu,sans-serif',
                            size: 12,
                            color: '#787b86'
                          }
                        };
    
                        var trace = {
                            x: x,
                            type: 'histogram',
                          };
    
                        var data = [trace];
                        Plotly.newPlot('$chartId', data, layout);
                    """.trimIndent()
                )
            }
        }
    }
}
