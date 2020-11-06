package trading.correlations

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

data class Ticker(
    val date: Date,
    val price: Double
)

class TickerLoader {

    fun load(
        file: File,
        dateFormat: String,
        startDate: Date,
        endDate: Date = Date()
    ): List<Ticker> {
        val dateFormatter = SimpleDateFormat(dateFormat)
        return csvReader().readAll(file).map { Ticker(dateFormatter.parse(it[0]), it[1].toDouble()) }.filter { it.date in startDate..endDate }
    }

    fun alignStart(
        chart1: MutableList<Ticker>,
        chart2: MutableList<Ticker>
    ) {
        val formatter = SimpleDateFormat("YYYY MM")
        chart1.sortBy { it.date }
        chart2.sortBy { it.date }

        fun removeFirstItems(
            chart1: MutableList<Ticker>,
            chart2: MutableList<Ticker>
        ) {
            val startDate = formatter.format(chart2[0].date)
            val startIndex = chart1.indexOfFirst { formatter.format(it.date) == startDate }
            for (i in 0 until startIndex) {
                chart1.removeAt(0)
            }
        }

        if (chart1[0].date < chart2[0].date) {
            removeFirstItems(chart1, chart2)
        } else {
            removeFirstItems(chart2, chart1)
        }

        val min = chart1.size.coerceAtMost(chart2.size)
        for (i in min until chart1.size) chart1.removeLast()
        for (i in min until chart2.size) chart2.removeLast()
    }
}