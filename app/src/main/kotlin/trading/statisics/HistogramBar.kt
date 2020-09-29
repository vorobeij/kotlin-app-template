package trading.statisics

data class HistogramBar<T>(
    val start: T,
    val end: T,
    var count: Int
)
